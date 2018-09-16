package com.chong.bys.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chong.bys.remote.service.TestRemoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lichong
 * 2018/9/15 22:13
 * @version 1
 * @since 1.0
 */
@RestController
public class TestRemoteController {


    @Reference
    TestRemoteService testRemoteService;

    @GetMapping("/getSysUser")
    public String getSysUser(Long id){

        String userName = testRemoteService.getUserName(id.toString());
        return userName;
    }


}
