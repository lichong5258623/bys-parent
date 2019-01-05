package com.chong.bys.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chong.bys.artical.dto.ArticalDto;
import com.chong.bys.artical.service.ArticalService;
import com.chong.bys.service.MyUserDetailsService;
import com.chong.bys.user.api.serivce.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lichong
 * @version 1
 * @date 2018/8/15 21:30
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Reference
    ArticalService articalService;

    @Autowired
    MyUserDetailsService userDetailsService;

    @GetMapping("/getArticalById")
    public ArticalDto getArticalById(Long id) {

        return articalService.selectArticalById(id);
    }

    @RequestMapping("testUsermodel")
    public UserDetails testUsermodel(){
        UserDetails licihong01 = userDetailsService.loadUserByUsername("lichong01");
        return licihong01;
    }


}
