package com.chong.bys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @author lichong
 * @version 1
 * @date 2018/9/4 22:43
 * @since 1.0
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    AuthenticationFailureHandler imoocAuthenctiationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.formLogin()
                .loginPage("/loginPage.html")
                .loginProcessingUrl("/authentication/form")
//                .failureForwardUrl("/failureLoginPage") //若设置失败则自定义失败处理器不生效，已测试
                .failureHandler(imoocAuthenctiationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/staticPages/**","/error","/loginPage.html","/index").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }


    /**
     * springsecurity默认提供的加密类，此加密每次加密后的密码都不一样，但是可以正确匹配密码，建议使用
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
