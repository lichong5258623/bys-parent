package com.chong.bys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
@EnableElasticsearchRepositories
@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {

		/**
		 * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
		 * 解决netty冲突后初始化client时还会抛出异常
		 * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
		 */
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication.run(WebApplication.class, args);
		log.info("bysApplication is success!项目启动成功");
	}
}
