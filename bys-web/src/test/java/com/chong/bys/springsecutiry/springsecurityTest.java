package com.chong.bys.springsecutiry;

import com.chong.bys.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lichong
 * 2018/9/9 5:27
 * @version 1
 * @since 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class springsecurityTest {


    @Autowired
    MyUserDetailsService myUserDetailsService;


    @Test
    public void test1(){

    }

}
