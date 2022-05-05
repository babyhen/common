package com.pawpaw.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class JacksonUtil {

    private final ObjectMapper objectMapper;

    public JacksonUtil() {
        this.objectMapper = new ObjectMapper();
        DeserializationConfig config = this.objectMapper.getDeserializationConfig();
        config = config.without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setConfig(config);
    }

    public JacksonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    /**
     * json转换成指定类型的对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     * @throws IOException
     * @throws JsonMappingException
     */
    public <T> T json2Object(String jsonStr, Class<T> clazz) {
        T t = null;
        try {
            t = this.objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    /**
     * json转换成指定类型的对象
     *
     * @param jsonStr
     * @throws Exception
     * @throws IOException
     * @throws JsonMappingException
     * @throws Exception
     */
    public <T> T json2Object(String jsonStr, TypeReference<T> typeReference) {
        T t = null;
        try {
            t = this.objectMapper.readValue(jsonStr, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;

    }


    /**
     * 对象转json
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public String object2Json(Object obj) {
        try {
            return this.objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
