package com.chong.bystest.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * rabbitmq测试demo 向rabbitmq 发送数据
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {


    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    public void test1(){

        HashMap<String, Object> map = new HashMap<>();

        map.put("name","lichong");
        map.put("age","23");
        map.put("said","明天会更好");
        rabbitTemplate.convertAndSend("exchange.direct","lichong.news",map);

    }


    @Test
    public void consumerDirect(){

        Object o = rabbitTemplate.receiveAndConvert("lichong.news");
        System.out.println(o);
    }

}
