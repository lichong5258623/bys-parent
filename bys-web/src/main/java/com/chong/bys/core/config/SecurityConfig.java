package com.chong.bys.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lichong
 * 2018/9/15 21:55
 * @version 1
 * @since 1.0
 */
@Configuration
public class SecurityConfig {


    /**
     * springsecurity默认提供的加密类，此加密每次加密后的密码都不一样，但是可以正确匹配密码，建议使用
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
