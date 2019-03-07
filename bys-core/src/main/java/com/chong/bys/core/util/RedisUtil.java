package com.chong.bys.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 封装redisTemplate,简化操作
 */
@Component
public class RedisUtil {

    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setObject(String key, Object object) {

        stringRedisTemplate.opsForValue().set(key, jsonUtil.BeanToJson(object));
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public <T> T getObjet(String key, Class<T> clazz) {
        return jsonUtil.JsonToBean(stringRedisTemplate.opsForValue().get(key), clazz);
    }

}
