/**************************************************************************
 * Copyright (c) 2006-2888 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 *
 * 项目名称：义乌市场采购
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.chong.bys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chong.bys.domain.pojo.SysUser;
import com.chong.bys.service.SysUserService;

import javax.servlet.http.HttpSession;

/**
 * @author lichong
 * @version 1
 * @date 2018/8/15 21:30
 * @since 1.0
 */
@Controller
public class IndexController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("/index")
    public String index(Model model){

        model.addAttribute("name","lichong");
        model.addAttribute("hello","hello");

        return "index";
    }

    /**
     * 异常测试，传入id为2报异常
     * @param id
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    public SysUser getSysUser(Long id){
        if(id==2){
            throw new RuntimeException("报异常了");
        }
        return sysUserService.selectById(id);
    }


    @GetMapping("/getSessionId")
    @ResponseBody
    public String getSessionId(HttpSession session){

        return session.getId();
    }

}
