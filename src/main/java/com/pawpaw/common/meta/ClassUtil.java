package com.pawpaw.common.meta;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * 类的工具类
 */
public class ClassUtil {

    public static <T> List<ParamInfo> getParamInfo(Constructor<T> constructor) {
        Param[] pArray;
        Params annotation = constructor.getAnnotation(Params.class);
        if (annotation == null) {
            Param p = constructor.getAnnotation(Param.class);
            if (p == null) {
                pArray = new Param[0];
            } else {
                pArray = new Param[]{p};
            }
        } else {
            pArray = annotation.value();
        }
        List<ParamInfo> r = new LinkedList<>();
        for (Param p : pArray) {
            String pName = p.value();
            String dv = p.defaultValue();
            Class type = p.type();
            r.add(new ParamInfo(pName, dv, type));
        }
        return r;
    }


    public static List<ParamInfo> getParamInfo(Method method) {
        Param[] pArray;
        Params annotation = method.getAnnotation(Params.class);
        if (annotation == null) {
            Param p = method.getAnnotation(Param.class);
            if (p == null) {
                pArray = new Param[0];
            } else {
                pArray = new Param[]{p};
            }
        } else {
            pArray = annotation.value();
        }
        List<ParamInfo> r = new LinkedList<>();
        for (Param p : pArray) {
            String pName = p.value();
            String dv = p.defaultValue();
            Class type = p.type();
            r.add(new ParamInfo(pName, dv, type));
        }
        return r;
    }
}
