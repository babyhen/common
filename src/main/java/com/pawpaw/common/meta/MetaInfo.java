package com.pawpaw.common.meta;

import com.google.gson.reflect.TypeToken;
import com.pawpaw.common.json.JsonUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 构造方法的元数据信息
 *
 * @param <T>
 */
public class MetaInfo<T> {
    public final Class<? extends T> aClass;
    public final Constructor<? extends T> constructor;
    public final List<ParamInfo> paramInfos;
    private ConcurrentHashMap extraInfo = new ConcurrentHashMap();

    public MetaInfo(Constructor<? extends T> constructor) {
        this(constructor.getDeclaringClass(), constructor);
    }


    public MetaInfo(Class<? extends T> aClass, Constructor<? extends T> constructor) {
        this.aClass = aClass;
        this.constructor = constructor;
        //不可改变的list。防止被而已篡改里面的元素
        this.paramInfos = Collections.unmodifiableList(ClassUtil.getParamInfo(this.constructor));
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
        Map<String, String> paramValueMap = JsonUtil.json2Object(jsonStr, new TypeToken<Map<String, String>>() {
        });
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
            try {
                String paramName = pi.getName();
                Class Type = pi.getType();
                String value = paramValueMap.get(paramName);
                IConvertor convertor = pi.getConvertorClz().getDeclaredConstructor().newInstance();
                Object convertedValue = convertor.convert(value, Type);
                r[i] = convertedValue;
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

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


}

