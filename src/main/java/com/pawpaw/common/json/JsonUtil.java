package com.pawpaw.common.json;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

public class JsonUtil {

    public static final TypeToken<Set<String>> stringSetType = new TypeToken<Set<String>>() {
    };
    public static final TypeToken<List<String>> stringListType = new TypeToken<List<String>>() {
    };

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static JsonParser parser = new JsonParser();

    /**
     * json转换成指定类型的对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static <T> T json2Object(String jsonStr, Class<T> clazz) {
        T t = gson.fromJson(jsonStr, clazz);
        return t;
    }

    /**
     * json转换成指定类型的对象
     *
     * @param jsonStr
     * @return
     * @throws Exception
     * @throws IOException
     * @throws JsonMappingException
     * @throws Exception
     */
    public static <T> T json2Object(String jsonStr, TypeToken<T> typeToken) {
        return gson.fromJson(jsonStr, typeToken.getType());

    }

    /**
     * json转换成指定类型的对象
     *
     * @param jsonStr
     * @param type
     * @return
     */
    public static <T> T json2Object(String jsonStr, Type type) {
        return gson.fromJson(jsonStr, type);
    }

    /**
     * 对象转json
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String object2Json(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * 指定的字符串，把指定的key反序列化成指定的类型
     *
     * @param jsonStr
     * @param key
     * @param clz
     * @return
     */
    public static <T> T json2Object(String jsonStr, String key, Class<T> clz) {
        JsonElement je = parser.parse(jsonStr);
        JsonObject jo = je.getAsJsonObject();
        JsonElement target = jo.get(key);
        T r = gson.fromJson(target, clz);
        return r;
    }
}
