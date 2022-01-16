package com.pawpaw.common.meta;

import com.google.gson.reflect.TypeToken;
import com.pawpaw.common.util.JsonUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 构造方法的元数据信息
 *
 * @param <T>
 */
public class MetaInfo<T> {
    public final Class<? extends T> aClass;
    public final Constructor<? extends T> constructor;
    public final String[] defaultArgValue;
    public final List<ParamInfo> paramInfos;

    public MetaInfo(Constructor<? extends T> constructor) {
        this(constructor.getDeclaringClass(), constructor);
    }


    public MetaInfo(Class<? extends T> aClass, Constructor<? extends T> constructor) {
        this.aClass = aClass;
        this.constructor = constructor;
        this.defaultArgValue = this.defaultArgValue();
        this.paramInfos = ClassUtil.getParamInfo(this.constructor);
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
        Map paramValueMap = JsonUtil.json2Object(jsonStr, new TypeToken<Map>() {
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
            String paramName = pi.getName();
            Object value = paramValueMap.get(paramName);
            r[i] = value;
        }
        return r;
    }

    private String[] defaultArgValue() {
        List<ParamInfo> params = ClassUtil.getParamInfo(constructor);
        String[] r = new String[params.size()];
        for (int i = 0; i < params.size(); i++) {
            ParamInfo pi = params.get(i);
            r[i] = pi.getDefaultValue();
        }
        return r;
    }

    public List<ParamInfo> getConstructArgInfo() {
        return ClassUtil.getParamInfo(this.constructor);
    }
}

