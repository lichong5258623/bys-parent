package com.chong.bys.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {

	@Autowired
	MyRedisProperties myRedisProperties;

	@Bean
	public JedisPool JedisPool() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(myRedisProperties.getPoolMaxIdle());
		poolConfig.setMaxTotal(myRedisProperties.getPoolMaxTotal());
		poolConfig.setMaxWaitMillis(myRedisProperties.getPoolMaxWait() * 1000);
		return new JedisPool(poolConfig, myRedisProperties.getHost(), myRedisProperties.getPort(),
				myRedisProperties.getTimeout()*1000, myRedisProperties.getPassword(), 0);
	}


	@Bean
	public RedisTemplate<String, Object> redisTemplate(
			RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setDefaultSerializer(new GenericFastJsonRedisSerializer());
		return template;
	}

}
