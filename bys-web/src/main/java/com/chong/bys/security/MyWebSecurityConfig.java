package com.chong.bys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author lichong
 * @version 1
 * @date 2018/9/4 22:43
 * @since 1.0
 */
@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    AuthenticationFailureHandler bysAuthenctiationFailureHandler;


    @Autowired
    AuthenticationSuccessHandler bysAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/loginPage.html")
                .loginProcessingUrl("/authentication/form")
//                .failureForwardUrl("/failureLoginPage") //若设置失败则自定义失败处理器不生效，已测试
                .failureHandler(bysAuthenctiationFailureHandler)
                .successHandler(bysAuthenticationSuccessHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**","/staticPages/**","/error","/loginPage.html","/index").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
