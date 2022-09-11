package com.pawpaw.common;

import com.pawpaw.common.meta.MetaInfo;
import com.pawpaw.common.meta.Param;
import com.pawpaw.common.meta.ParamInfo;
import com.pawpaw.common.json.JsonUtil;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetaInfoTest {
    @Test
    public void getParamInfo() throws NoSuchMethodException {
        Constructor constructor = Son.class.getConstructor(
                Father.class,
                Integer.TYPE,
                String.class);
        MetaInfo mi = new MetaInfo<>(constructor);
        List<ParamInfo> params = mi.getParamInfos();
        for (ParamInfo pi : params) {
            System.out.println(pi);
        }
    }


    @Test
    public void deserializeconstructArgs() throws Exception {
        Constructor c = Father.class.getConstructor(Integer.class, String.class, Integer.TYPE);
        MetaInfo mi = new MetaInfo<>(c);
        Map<String, Object> pm = new HashMap<>();
        pm.put("z", 99);

        Object[] realParam = mi.deserializeconstructArgs(JsonUtil.object2Json(pm));
        for (Object o : realParam) {
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


