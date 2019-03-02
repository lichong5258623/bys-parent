package com.chong.bys;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableDubbo
@MapperScan("com.chong.bys.article.dao")
@SpringBootApplication
public class BysArticalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BysArticalServiceApplication.class, args);
        log.info("文章服务启动完毕......");
    }
}
