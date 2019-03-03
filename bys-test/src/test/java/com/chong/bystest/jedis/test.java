package com.chong.bystest.jedis;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

@Slf4j
public class test {


    private Jedis jedis = null;


    @Before
    public void before() {
        //jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(3);

        //实例化jedis连接池
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "47.101.68.18", 26379, 2000, "123456");
        jedis = jedisPool.getResource();
    }
    @After
    public void after(){
        jedis.close();
    }

    @Test
    public void test1() {

        String first = jedis.get("first");
        log.info("已有设置：" + first);
        jedis.set("second", "second");
        String second = jedis.get("second");



        log.info("jedis设置：" + second);
        jedis.select(2);
        jedis.set("db2", "db2");
        jedis.incr("aaa");
    }

    @Test
    public void test2() {

        long start = System.currentTimeMillis();
        Long incr = jedis.incr("001");
        long end = System.currentTimeMillis();
        log.info(end - start + "");
        long start2 = System.currentTimeMillis();

        String s = jedis.get("001");
        long end2 = System.currentTimeMillis();
        log.info(end2 - start2 + "");
        log.info(s);
        log.info(incr.toString());
    }

    @Test
    public void testhash() {

        jedis.select(3);
        jedis.set("demo01", "demo01你好");

        String demo01 = jedis.get("demo01");
        log.info(demo01);
        jedis.hset("user_32", "id", "32");

        String hget = jedis.hget("user_32", "id");
        log.info(hget);


    }


    @Test
    public void testList() {
        jedis.lpush("listKey","3","2","1");
        jedis.rpush("listKey","4","5");
        List<String> listKey = jedis.lrange("listKey", 0, 10);
        for (String s : listKey) {
            System.out.print(s);
        }
        jedis.del("listKey");
    }

    /**
     * bitmaps 练习demo，可用做用户是否在线做统计
     * 一亿2千8百万用户的模拟环境下，在一台MacBookPro上，典型的统计如“日用户数”的时间消耗小于50ms, 占用16MB内存
     */
    @Test
    public void testSet(){
        jedis.setbit("user",1121L,true);
        jedis.setbit("user",1131L,true);
        jedis.setbit("user",1141L,true);
        jedis.setbit("user",1151L,true);
        jedis.setbit("user",11651L,true);

        Boolean user = jedis.getbit("user", 1121L);
        log.info(user+"");
        Boolean user2 = jedis.getbit("user", 1121444L);
        log.info(user2+"");

        Long user1 = jedis.bitcount("user");
        log.info(user1.toString());
    }


    public void testRDB(){
        jedis.bgsave();
    }

}
