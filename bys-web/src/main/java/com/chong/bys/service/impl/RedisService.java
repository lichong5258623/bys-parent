package com.chong.bys.service.impl;

import com.chong.bys.core.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Slf4j
@Service
public class RedisService {


    @Autowired
    JsonUtil jsonUtil;

    @Autowired
    private JedisPool jedisPool;


    private StringRedisTemplate redisTemplate;


    /**
     * 向redis中添加值
     *
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> Boolean setValue(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String code = jedis.set(key, jsonUtil.BeanToJson(value));
            log.info(code);
            if ("OK".equals(code)) {

                return true;
            }
            return false;
        } finally {
            returnToPool(jedis);
        }

    }

    /**
     * 从redis中获取值
     *
     * @param key
     * @param clazz
     * @param <T>   存在redis中的类型
     * @return
     */
    public <T> T getValue(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String s = jedis.get(key);
            return jsonUtil.JsonToBean(s, clazz);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断key是否存在
     */
    public <T> boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            returnToPool(jedis);
        }
    }

    public Long incr(String key){

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            return jedis.incr(key);
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 回收redis连接
     *
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
