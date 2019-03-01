package com.chong.bys.user;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@EnableDubbo
@SpringBootApplication
@MapperScan("com.chong.bys.user.dao")
public class BysUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BysUserServiceApplication.class, args);
        log.info("用户服务启动完毕");
    }
}
