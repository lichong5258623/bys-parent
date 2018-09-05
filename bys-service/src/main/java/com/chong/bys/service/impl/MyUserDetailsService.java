package com.chong.bys.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author lichong
 * @version 1
 * @date 2018/9/4 22:53
 * @since 1.0
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("用户名"+username);

        //TODO 根据用户名查找用户信息

        String passwordEncode = passwordEncoder.encode("123456");

        log.info("加密后的密码"+passwordEncode);
        User admin = new User(username, passwordEncode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return admin;
    }
}
