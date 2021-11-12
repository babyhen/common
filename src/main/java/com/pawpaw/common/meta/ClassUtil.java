package com.pawpaw.common.meta;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 类的工具类
 */
public class ClassUtil {

    public static <T> List<ParamInfo> getParamInfo(Constructor<T> constructor) {
        Params annotation = constructor.getAnnotation(Params.class);
        if (annotation == null) {
            return Collections.emptyList();
        }
        List<ParamInfo> r = new LinkedList<>();
        for (Param p : annotation.value()) {
            String pName = p.value();
            String dv = p.defaultValue();
            Class type = p.type();
            r.add(new ParamInfo(pName, dv, type));
        }
        return r;
    }


    public static List<ParamInfo> getParamInfo(Method method) {
        Params annotation = method.getAnnotation(Params.class);
        if (annotation == null) {
            return Collections.emptyList();
        }
        List<ParamInfo> r = new LinkedList<>();
        for (Param p : annotation.value()) {
            String pName = p.value();
            String dv = p.defaultValue();
            Class type = p.type();
            r.add(new ParamInfo(pName, dv, type));
        }
        return r;
    }
}
