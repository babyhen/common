package com.pawpaw.common.meta;

import com.pawpaw.common.json.JsonUtil;
import com.pawpaw.common.util.ClassUtils;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 构造方法的元数据信息
 * todo会有循环引用的问题
 *
 * @param <T>
 */
@Getter
public class MetaInfo<T> {
    private final Class<? extends T> aClass;
    private final Constructor<? extends T> constructor;
    //key：参数在方法的位置， value：参数的信息
    private final Map<Integer, AbstractParamInfo> paramInfoMap;
    private final ConcurrentHashMap extraInfo;


    public MetaInfo(Constructor<? extends T> constructor) {
        this.aClass = constructor.getDeclaringClass();
        this.constructor = constructor;
        this.extraInfo = new ConcurrentHashMap();
        //不可改变的list。防止被而已篡改里面的元素
        Map<Integer, AbstractParamInfo> analysedParamInfo = this.analyseParamInfo(this.constructor);
        this.paramInfoMap = Collections.unmodifiableMap(analysedParamInfo);
    }

    public void addExtra(Object key, Object value) {
        this.extraInfo.put(key, value);
    }

    public Object removeExtra(Object key) {
        return this.extraInfo.remove(key);
    }


    @Override
    public String toString() {
        return aClass.getName();
    }

    /**
     * @return
     */
    public List<AbstractParamInfo> getParamInfoList() {
        List<AbstractParamInfo> r = new LinkedList<>();
        r.addAll(this.paramInfoMap.values());
        return r;
    }

    /**
     * 得到该 metaInfo下的每一个 AbstractParamInfo的所有基础类型的，带param注解的字段信息
     *
     * @return
     */
    public List<AbstractParamInfo> getRecursionPrimaryFields() {
        List<AbstractParamInfo> r = new LinkedList<>();
        for (AbstractParamInfo pi : this.getParamInfoList()) {
            List<AbstractParamInfo> subFields = pi.getRecursionPrimaryFields();
            if (subFields.isEmpty()) {
                r.add(pi);
            } else {
                r.addAll(subFields);
            }
        }
        return r;
    }

    /**
     * 根据提供的参数和元信息。反序列化构造函数的参数值
     * 方法的参数上可能没有@Param注解。没有的地方需要填上null
     *
     * @param jsonStr
     * @return
     */
    public Object[] deserializeconstructArgs(String jsonStr) {
        Parameter[] realParam = this.constructor.getParameters();
        Object[] r = new Object[realParam.length];
        //依次遍历，看看参数的位置是否有@Param注解。
        // 如果有。则根据参数名字从提供的json字符串中搜索对应的值
        //如果没有则设置成null
        for (int position = 0; position < realParam.length; position++) {
            AbstractParamInfo pi = this.paramInfoMap.get(position);
            if (pi == null) {
                r[position] = null;
                continue;
            }
            String paramName = pi.getName();
            Class type = pi.getType();
            Object value = JsonUtil.json2Object(jsonStr, paramName, type);
            r[position] = value;
        }
        return r;
    }


    /**  ************************  help method   ********************************************/
    /**  ********************************************************************/
    /**  ********************************************************************/
    /**
     * 方法的参数上的注解
     *
     * @return
     */
    private Map<Integer, AbstractParamInfo> analyseParamInfo(Parameter[] parameters) {
        Map<Integer, AbstractParamInfo> r = new HashMap<>();
        ComplexTypeParamInfo rootParamInfo = new ComplexTypeParamInfo("root", this.aClass, "", null, null, null);
        //遍历方法上的参数.
        for (int position = 0; position < parameters.length; position++) {
            Parameter parameter = parameters[position];
            Param param = parameter.getAnnotation(Param.class);
            //没有注解，则直接下一个参数
            if (param == null) {
                continue;
            }
            //构造对应的对象，并加入到返回的列表里面
            String name = param.value();
            Class<?> type = parameter.getType();
            DefaultValue dfv = parameter.getAnnotation(DefaultValue.class);
            DefaultValues dfvs = parameter.getAnnotation(DefaultValues.class);
            String desc = param.desc();
            if (isPrimaryType(type)) {
                PrimaryTypeParamInfo ptpi = new PrimaryTypeParamInfo(name, type, desc, rootParamInfo, dfv, dfvs);
                r.put(position, ptpi);
            } else {
                ComplexTypeParamInfo ctpi = new ComplexTypeParamInfo(name, type, desc, rootParamInfo, dfv, dfvs);
                this.analyseField(type, ctpi);
                r.put(position, ctpi);
            }
        }
        //
        rootParamInfo.addField(r.values());
        rootParamInfo.check();
        //
        return r;
    }


    /**
     * 给定一个类，递归判断他的field以及每个field的@Param注解的信息
     *
     * @param clz
     * @param parentObj
     */
    public void analyseField(Class clz, ComplexTypeParamInfo parentObj) {
        List<Field> fields = ClassUtils.getDeclaredFields(clz);
        for (Field f : fields) {
            Param param = ClassUtils.getAnnotation(f, Param.class);
            if (param == null) {
                continue;
            }
            DefaultValue dfv = ClassUtils.getAnnotation(f, DefaultValue.class);
            DefaultValues dfvs = ClassUtils.getAnnotation(f, DefaultValues.class);
            Class fieldClz = f.getType();
            String name = param.value();
            String desc = param.desc();

            //如果字段是基础类型
            if (isPrimaryType(fieldClz)) {
                PrimaryTypeParamInfo fieldInfo = new PrimaryTypeParamInfo(name, fieldClz, desc, parentObj, dfv, dfvs);
                parentObj.addField(fieldInfo);
            } else {
                //如果是符合的类型，那么递归调用
                ComplexTypeParamInfo ctpi = new ComplexTypeParamInfo(name, fieldClz, desc, parentObj, dfv, dfvs);
                analyseField(fieldClz, ctpi);
                parentObj.addField(ctpi);
            }
        }


    }

    private Map<Integer, AbstractParamInfo> analyseParamInfo(Method method) {
        return this.analyseParamInfo(method.getParameters());
    }

    private <T> Map<Integer, AbstractParamInfo> analyseParamInfo(Constructor<T> constructor) {
        return this.analyseParamInfo(constructor.getParameters());
    }

    /**
     * 是否是基础类型
     *
     * @param clz
     * @return
     */
    private boolean isPrimaryType(Class clz) {
        if (clz == String.class) {
            return true;
        }
        if (clz == Date.class) {
            return true;
        }
        if (clz == Boolean.class || clz == Boolean.TYPE) {
            return true;
        }
        if (clz == Byte.class || clz == Byte.TYPE) {
            return true;
        }
        if (clz == Short.class || clz == Short.TYPE) {
            return true;
        }
        if (clz == Integer.class || clz == Integer.TYPE) {
            return true;
        }
        if (clz == Long.class || clz == Long.TYPE) {
            return true;
        }
        if (clz == Float.class || clz == Float.TYPE) {
            return true;
        }
        if (clz == Double.class || clz == Double.TYPE) {
            return true;
        }
        return false;
    }
}

