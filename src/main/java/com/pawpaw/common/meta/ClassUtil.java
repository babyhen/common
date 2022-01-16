package com.pawpaw.common.meta;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 * 类的工具类
 */
public class ClassUtil {

    public static <T> List<ParamInfo> getParamInfo(Constructor<T> constructor) {
        return getParamInfo(constructor.getParameters());
    }

    /**
     * 方法的参数上的注解
     *
     * @return
     */
    public static List<ParamInfo> getParamInfo(Parameter[] parameters) {
        List<ParamInfo> r = new LinkedList<>();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Param param = parameter.getAnnotation(Param.class);
            if (param == null) {
                continue;
            }
            Class<?> type = parameter.getType();
            ParamInfo t = new ParamInfo(i, param.value(), param.defaultValue(), type, param.desc());
            r.add(t);
        }
        return r;
    }


    public static List<ParamInfo> getParamInfo(Method method) {
        return getParamInfo(method.getParameters());
    }
}
