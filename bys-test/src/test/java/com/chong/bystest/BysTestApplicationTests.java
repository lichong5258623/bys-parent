package com.chong.bystest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BysTestApplicationTests {

	@Autowired
	private RabbitAdmin rabbitAdmin;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void contextLoads() {
		System.out.println(rabbitAdmin);
		System.out.println(rabbitTemplate);
	}

	@Test
	public void test01(){
		String msg = "Hello World Msg";
		Message message = new Message(msg.getBytes(), null);
		rabbitTemplate.convertAndSend("sb-test-exchange","springboot.test1" , message);
	}
}

