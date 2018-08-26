package com.chong.bys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author lichong
 * @date 2018/08/19 22:19
 * @since 1.0
 */
@Slf4j
@EnableAsync //开启异步支持
@EnableCaching //开启缓存注解模式
@EnableRedisHttpSession //开启redisSession
@EnableRabbit //开启rabbitmq注解模式
@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
		log.info("bysApplication is success!项目启动成功");
	}
}
