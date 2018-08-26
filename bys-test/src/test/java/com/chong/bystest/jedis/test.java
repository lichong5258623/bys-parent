package com.chong.bystest.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
public class test {


    private JedisPool jedisPool = null;


    @Before
    public void before() {
        //jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(3);

        //实例化jedis连接池
        jedisPool = new JedisPool(jedisPoolConfig, "60.205.206.199", 6379, 2000, "123456");
    }


    @Test
    public void test1() {

        //获取jedis实例
        Jedis jedis = jedisPool.getResource();
        String first = jedis.get("first");
        log.info("已有设置：" + first);
        jedis.set("second", "second");
        String second = jedis.get("second");
        log.info("jedis设置：" + second);
        jedis.select(2);
        jedis.set("db2", "db2");
        jedis.incr("aaa");
        jedis.close();
    }

    @Test
    public void test2() {
        Jedis jedis = jedisPool.getResource();

        long start = System.currentTimeMillis();
        Long incr = jedis.incr("001");
        long end = System.currentTimeMillis();
        log.info(end - start + "");
        long start2 = System.currentTimeMillis();

        String s = jedis.get("001");
        long end2 = System.currentTimeMillis();
        log.info(end2-start2+"");
        log.info(s);
        log.info(incr.toString());
        jedis.close();
    }


}
