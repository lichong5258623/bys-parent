package com.chong.bystest.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能说明：
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @since 3.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbimtmqTest {
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test01(){
        String msg = "Hello World Msg";
        Message message = new Message(msg.getBytes(), null);
        rabbitTemplate.convertAndSend("sb-test-exchange","springboot.test1" , message);
    }
}
