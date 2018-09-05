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

import com.chong.bys.base.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chong.bys.domain.pojo.SysUser;
import com.chong.bys.service.SysUserService;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lichong
 * @version 1
 * @date 2018/8/15 21:30
 * @since 1.0
 */
@Slf4j
@Controller
public class IndexController {

    @Autowired
    SysUserService sysUserService;


    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping("/index")
    public String index(Model model){

        model.addAttribute("name","lichong");
        model.addAttribute("hello","hello");

        return "index";
    }

    @GetMapping("/loginType")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result<String> loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, "/loginPage");
            }
        }
        return Result.error(401, "访问的服务需要身份认证，请引导用户到登录页");
    }

    @GetMapping("/loginPage")
    public String loginPage(){

        return "loginPage";
    }

    /**
     * 异常测试，传入id为2报异常
     * @param id
     * @return
     */
    @GetMapping("/getSysUser")
    @ResponseBody
    public SysUser getSysUser(Long id){
        if(id==2){
            int i = 2/0;
        }
        return sysUserService.selectById(id);
    }


    @GetMapping("/getSessionId")
    @ResponseBody
    public String getSessionId(HttpSession session){

        return session.getId();
    }

}
