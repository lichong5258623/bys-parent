package com.chong.bys.config;

import com.chong.bys.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring MVC 配置类
 * @author lichong
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 添加拦截器
     * @param registry 拦截器注册类
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LogInterceptor());
    }
}
