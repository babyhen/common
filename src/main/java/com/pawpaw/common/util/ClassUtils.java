package com.pawpaw.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassUtils {

    /**
     * 得到所有直接声明的field，包括 public ，private ，protect和默认的（同包级）
     * 不包括从父类继承而来的field
     *
     * @param clz
     * @return
     */
    public static List<Field> getDeclaredFields(Class clz) {
        Field[] fields = clz.getDeclaredFields();
        return Arrays.asList(fields);
    }

    /**
     * 得到所有的构造函数
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<Constructor<T>> getAllConstructor(Class<T> clz) {
        Constructor[] cs = clz.getDeclaredConstructors();
        return Arrays.asList(cs);
    }

    /**
     * 得到构造函数上指定的注解
     *
     * @param constructor
     * @param annotationClass
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Constructor constructor, Class<T> annotationClass) {
        Annotation t = constructor.getAnnotation(annotationClass);
        return (T) t;
    }

    /**
     * 返回该field的指定注解
     *
     * @param field
     * @param annotation
     * @param <T>
     * @return
     */
    public static <T extends Annotation> T getAnnotation(Field field, Class<T> annotation) {
        T t = field.getAnnotation(annotation);
        return t;
    }


}
