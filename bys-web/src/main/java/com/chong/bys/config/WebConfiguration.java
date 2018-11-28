package com.chong.bys.config;

import com.chong.bys.interceptor.LogInterceptor;
import com.chong.bys.interceptor.ThreadLocalInterceptor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * spring MVC 配置类
 *
 * @author lichong
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 添加拦截器
     *
     * @param registry 拦截器注册类
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
        registry.addInterceptor(new ThreadLocalInterceptor());
    }


    /**
     * hibernate validator 设置
     *
     * @return
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                //设置为快速失败就，即有一个参数没通过就返回失败，默认是返回全部错误信息
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

}
