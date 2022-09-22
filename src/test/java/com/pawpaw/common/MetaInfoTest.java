package com.pawpaw.common;

import com.pawpaw.common.json.JsonUtil;
import com.pawpaw.common.meta.*;
import lombok.ToString;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MetaInfoTest {

    static MetaInfo mi;
    static Father f;
    static Son son;

    @BeforeClass
    public static void init() throws NoSuchMethodException {
        Constructor constructor = Son.class.getConstructor(
                Father.class,
                Integer.TYPE,
                String.class);
        mi = new MetaInfo<>(constructor);
        f = new Father(34, "刘继新");
        son = new Son(f, 3, "刘绰");

    }


    @Test
    public void getParamInfoMap() throws NoSuchMethodException {
        Map<Integer, AbstractParamInfo> params = mi.getParamInfoMap();
        for (int i = 0; i < params.size(); i++) {
            System.out.println(params.get(i));
        }
    }


    @Test
    public void deserializeconstructArgs() throws Exception {

        String rawJson = JsonUtil.object2Json(son);
        System.out.println(rawJson);
        Object[] allArg = mi.deserializeconstructArgs(rawJson);
        for (Object o : allArg) {
            System.out.println(o);
        }
    }

    @Test
    public void getRecursionPrimaryFields() throws Exception {
        List<AbstractParamInfo> list = mi.getRecursionPrimaryFields();
        for (AbstractParamInfo pi : list) {
            System.out.println(pi.getName() + pi.getDefaultValue());
        }
    }

    @Test
    public void getParentChain() throws Exception {
        List<AbstractParamInfo> allFields = mi.getRecursionPrimaryFields();
        //
        for (AbstractParamInfo pi : allFields) {
            pi.getParentChain().forEach(e -> {
                System.out.print(e.getName() + "->" + e.getType() + "  ");
            });
            System.out.println("****");
        }
    }

}


class Grandpa {
    @Param(value = "grandpaAge")
    @DefaultValue("80")
    private int age;

    @DefaultValue("刘德旺")
    @Param("grandpaName")
    private String name;

    public Grandpa(int age, String name) {
        this.age = age;
        this.name = name;
    }


}


@ToString
class Father {
    @Param(value = "fatherAge")
    @DefaultValue("40")
    private int age;
    @Param("fatherName")
    private String name;

    //  @DefaultValue(value = "90", path = "grandpaAge")
    @DefaultValues({@DefaultValue(value = "90", path = "grandpaAge"), @DefaultValue(value = "赵凤芹", path = "grandpaName"),})
    @Param("grandpa")
    private Grandpa grandpa;

    public Father(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void say(String msg) {
        System.out.println(this.getClass().getSimpleName() + " age:" + this.age + " name:" + this.name + "  " + msg);
    }
}

class Son {
    private Father father;
    private int age;
    private String name;

    public Son( @DefaultValue(path="grandpa.grandpaAge",value = "150")  @Param("father") Father father,
               @Param("age") int age,
               @Param("name") String name) {
        this.father = father;
        this.age = age;
        this.name = name;
    }

    public void say(String msg) {
        this.father.say(msg);
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" age:" + this.age);
        sb.append(" name:" + this.name);
        sb.append("  " + msg);
        System.out.println(sb.toString());
    }
}


