package com.chong.nacosdemo;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "bysc-privder01", autoRefreshed = true)
public class NacosDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosDemoApplication.class, args);
	}


}
