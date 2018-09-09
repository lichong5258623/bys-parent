package com.chong.bys.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 封装jackon，使用fastjson解析复杂对象时解析不全
 * 此工具类交由spring来管理，可以统一配置jackon
 */
@Slf4j
@Component
public class JsonUtil {

    private final ObjectMapper objectMapper;

    @Autowired
    public JsonUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 实体转成Json格式字符串
     *
     * @param object 需要转换的对象
     * @return json字符串
     */
    public String BeanToJson(Object object) {
        String string = null;
        try {
            string = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.info("对象解析成json失败：{}", e.getMessage());
        }
        return string;
    }

    /**
     * 把字符串转对应的Bean
     *
     * @param jsonStr json字符串
     * @param clazz   需要转换对象的类对象
     * @param <T>     泛型
     * @return 对象
     */
    public <T> T JsonToBean(String jsonStr, Class<T> clazz) {

        T entity = null;
        try {
            entity = objectMapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            log.info("json转对象失败:{}", e.getMessage());
        }
        return entity;
    }

}
