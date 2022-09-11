package com.pawpaw.common;

import com.pawpaw.common.meta.MetaInfo;
import com.pawpaw.common.meta.Param;
import com.pawpaw.common.meta.ParamInfo;
import com.pawpaw.common.json.JsonUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
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
    public void getParamInfo() throws NoSuchMethodException {
        List<ParamInfo> params = mi.getParamInfos();
        for (ParamInfo pi : params) {
            System.out.println(pi);
        }
    }


    @Test
    public void deserializeconstructArgs() throws Exception {
        Father f = new Father(35, "刘继新");
        Son son = new Son(f, 3, "刘绰");
        String rawJson = JsonUtil.object2Json(son);
        System.out.println(rawJson);
        Object[] allArg = mi.deserializeconstructArgs(rawJson);
        for (Object o : allArg) {
            System.out.println(o);
        }
    }
}


class Father {
    private int age;
    private String name;

    public Father(@Param("age") int age, @Param("name") String name) {
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


