package com.chong.bystest.spring.annotation;

import com.chong.bystest.lambda.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

@Slf4j
public class SpringAnnotationTest {


    @Test
    public void test1(){


        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguratioin.class);
        Employee name = (Employee) applicationContext.getBean("employee");
        log.info("spring获取的name:{}",name);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {

            log.info("spring容器的对象：{}",beanDefinitionName);
        }


        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        Map<String, Object> systemEnvironment = environment.getSystemProperties();
        for (String key:systemEnvironment.keySet()){

            log.info("key为:{}。值为：{}",key,systemEnvironment.get(key));
        }
        log.info(property);


    }


    @Test
    public void test02(){

        Class<Employee> employeeClass = Employee.class;
        String s = employeeClass.toString();
        log.info("aa:"+s);
        
    }


}
