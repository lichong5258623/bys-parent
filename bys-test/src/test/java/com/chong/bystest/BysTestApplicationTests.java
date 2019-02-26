package com.chong.bystest;

import com.chong.bystest.config.DbInfo;
import com.chong.bystest.config.DynamicDataSourceProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BysTestApplicationTests {

	@Autowired
	DynamicDataSourceProperties dynamicDataSourceProperties;

	@Test
	public void contextLoads() {
		DbInfo master = dynamicDataSourceProperties.getMaster();
		System.out.println(master);
		List<DbInfo> slaves = dynamicDataSourceProperties.getSlaves();
		System.out.println(slaves);
	}

}

