package com.chong.bystest.spring.annotation;

import com.chong.bystest.lambda.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//配置类，等同于配置文件
@Configuration//告诉spirng这是一个配置类
public class SpringConfiguratioin {

    /**
     * scope注解值得属性：
     *
     * prototype：多实例的：ioc容器启动并不会去调用方法创建对象放在容器中。
     * 					每次获取的时候才会调用方法创建对象；
     * singleton：单实例的（默认值）：ioc容器启动会调用方法创建对象放到ioc容器中。
     * 			以后每次获取就是直接从容器（map.get()）中拿，
     * request：同一次请求创建一个实例
     * session：同一个session创建一个实例
     *
     * lazy注解懒加载：
     * 		单实例bean：默认在容器启动的时候创建对象；
     * 		懒加载：容器启动不创建对象。第一次使用(获取)Bean创建对象，并初始化；
     */
//    @Scope()
//    @Lazy
    //给容器注册一个bean；类型为返回值类型，id若不设置默认为方法名
    @Bean(name = "employee")
    public Employee employee(){

        return new Employee();
    }


}
