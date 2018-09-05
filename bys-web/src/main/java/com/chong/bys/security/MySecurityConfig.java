package com.chong.bys.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lichong
 * @version 1
 * @date 2018/9/4 22:43
 * @since 1.0
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http.formLogin()
                .loginPage("/loginType")
                .loginProcessingUrl("/authentication/form")
//                .failureForwardUrl()
                .and()
                .authorizeRequests()
                .antMatchers("/staticPages/**","/loginType","/loginPage","/index").permitAll()
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
