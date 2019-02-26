package com.chong.bystest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BysTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BysTestApplication.class, args);
	}

}

