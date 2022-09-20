package com.pawpaw.common;

import com.pawpaw.common.json.JsonUtil;
import com.pawpaw.common.meta.AbstractParamInfo;
import com.pawpaw.common.meta.DefaultValue;
import com.pawpaw.common.meta.MetaInfo;
import com.pawpaw.common.meta.Param;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Map;

public class MetaInfoTest {

    static MetaInfo mi;

    @BeforeClass
    public static void init() throws NoSuchMethodException {
        Constructor constructor = Son.class.getConstructor(
                Father.class,
                Integer.TYPE,
                String.class);
        mi = new MetaInfo<>(constructor);


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
        Father f = new Father(34, "刘继新");
        Son son = new Son(f, 3, "刘绰");
        String rawJson = JsonUtil.object2Json(son);
        System.out.println(rawJson);
        Object[] allArg = mi.deserializeconstructArgs(rawJson);
        for (Object o : allArg) {
            System.out.println(o);
        }
    }
}


class Grandpa {
    @Param(value = "age")
    @DefaultValue("80")
    private int age;

    @DefaultValue("刘德旺")
    @Param("name")
    private String name;

    public Grandpa(int age, String name) {
        this.age = age;
        this.name = name;
    }


}


class Father {
    @Param(value = "age")
    @DefaultValue("40")
    private int age;
    @Param("name")
    private String name;

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

    public Son(@Param("father") Father father,
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


