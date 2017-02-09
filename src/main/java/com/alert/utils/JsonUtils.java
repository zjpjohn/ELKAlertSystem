package com.alert.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * Project: AlertSystem
 * Module Desc:com.juntu.alert.utils
 * User: zjprevenge
 * Date: 2016/11/9
 * Time: 9:40
 */
public class JsonUtils {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        //反序列json串中存在bean中不存在的属性，将忽略
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //禁止空空对象转换序列化成json
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //设置为null的值不参与序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static <T> T json2Bean(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }

    public static String bean2Json(Object bean) throws Exception {
        return objectMapper.writeValueAsString(bean);
    }

    /**
     * 获取json字符串中指定字段的值
     *
     * @param json json字符串
     * @param name 字段名称
     * @return
     */
    public static String getJsonFieldValue(String json, String name) throws Exception {
        Preconditions.checkArgument(StringUtils.isNotBlank(json), "json must not be empty...");
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "name must not be empty...");
        JsonNode parentNode = objectMapper.readTree(json);
        JsonNode fieldNode = parentNode.path(name);
        return fieldNode.textValue();
    }
}
