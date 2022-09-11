package com.pawpaw.common.meta;

import com.pawpaw.common.json.JsonUtil;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 构造方法的元数据信息
 *
 * @param <T>
 */
@Getter
public class MetaInfo<T> {
    private final Class<? extends T> aClass;
    private final Constructor<? extends T> constructor;
    private final List<ParamInfo> paramInfos;
    private final ConcurrentHashMap extraInfo;


    public MetaInfo(Constructor<? extends T> constructor) {
        this.aClass = constructor.getDeclaringClass();
        this.constructor = constructor;
        //不可改变的list。防止被而已篡改里面的元素
        this.paramInfos = Collections.unmodifiableList(this.getParamInfo(this.constructor));
        this.extraInfo = new ConcurrentHashMap();
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
     * 根据提供的参数和元信息。反序列化构造函数的参数值
     * 方法的参数上可能没有@Param注解。没有的地方需要填上null
     *
     * @param jsonStr
     * @return
     */
    public Object[] deserializeconstructArgs(String jsonStr) {
        //实际参数的 参数名-》参数值
        //参数位置-》参数元数据的映射。
        Map<Integer, ParamInfo> paramInfoMap = paramInfos.stream().collect(Collectors.toMap(e -> e.getPosition(), e -> e));
        //
        Parameter[] realParam = this.constructor.getParameters();
        Object[] r = new Object[realParam.length];
        //依次遍历，看看参数的位置是否有@Param注解。
        // 如果有。则根据参数名字从提供的json字符串中搜索对应的值
        //如果没有则设置成null
        for (int i = 0; i < realParam.length; i++) {
            ParamInfo pi = paramInfoMap.get(i);
            if (pi == null) {
                r[i] = null;
                continue;
            }
            String paramName = pi.getName();
            Class type = pi.getType();
            Object value = JsonUtil.json2Object(jsonStr, paramName, type);
            r[i] = value;
        }
        return r;
    }

    public String[] defaultArgValue() {
        String[] r = new String[this.paramInfos.size()];
        for (int i = 0; i < this.paramInfos.size(); i++) {
            ParamInfo pi = this.paramInfos.get(i);
            r[i] = pi.getDefaultValue();
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
    private List<ParamInfo> getParamInfo(Parameter[] parameters) {
        List<ParamInfo> r = new LinkedList<>();
        Set<String> existName = new HashSet<>();
        for (int position = 0; position < parameters.length; position++) {
            Parameter parameter = parameters[position];
            Param param = parameter.getAnnotation(Param.class);
            if (param == null) {
                continue;
            }
            Class<?> type = parameter.getType();
            String name = param.value();
            //检查唯一性
            if (existName.contains(name)) {
                throw new MetaException("param \"" + name + "\" already exist!");
            }
            existName.add(name);
            //
            ParamInfo t = new ParamInfo(position, name, param.defaultValue(), type, param.desc());
            r.add(t);
        }
        return r;
    }

    private List<ParamInfo> getParamInfo(Method method) {
        return this.getParamInfo(method.getParameters());
    }

    private <T> List<ParamInfo> getParamInfo(Constructor<T> constructor) {
        return this.getParamInfo(constructor.getParameters());
    }

}

