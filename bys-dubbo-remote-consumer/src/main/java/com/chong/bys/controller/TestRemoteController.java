package com.chong.bys.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chong.bys.artical.dto.ArticalDto;
import com.chong.bys.artical.service.ArticalService;
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


//    @Reference
//    TestRemoteService testRemoteService;

    @Reference
    ArticalService articalService;

    @GetMapping("/getSysUser")
    public ArticalDto getSysUser(Long id) {
        return articalService.selectArticalById(id);
    }


}
