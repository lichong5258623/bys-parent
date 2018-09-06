package com.chong.bys.controller;

import com.chong.bys.exception.NoLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chong.bys.domain.pojo.SysUser;
import com.chong.bys.service.SysUserService;

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
public class IndexController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    private RequestCache requestCache = new HttpSessionRequestCache();

    @GetMapping("/index")
    public String index(Model model) {

        model.addAttribute("name", "lichong");
        model.addAttribute("hello", "hello");

        return "index";
    }

    @RequestMapping("/loginPage.html")
    public String loginType(HttpServletRequest request, HttpServletResponse response){
        //判断是否携带错误信息
        if(request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)!=null){

            log.info("错误信息：{}",request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));
            request.setAttribute("error", request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));
            return "loginPage";
        }
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String accept = request.getHeader("accept");
        log.info("请求头返回类型：{}", accept);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
            if (accept == null || !accept.contains("text/html")) {
                throw new NoLoginException("用户未登录，请引导用户登录");
            }
        }
        return "loginPage";
    }


    /**
     * 异常测试，传入id为2报异常
     *
     * @param id 用户id
     * @return
     */
    @GetMapping("/getSysUser")
    @ResponseBody
    public SysUser getSysUser(Long id) {
        if (id == 2) {
            int i = 2 / 0;
        }

        return sysUserService.selectById(id);
    }


    @GetMapping("/getSessionId")
    @ResponseBody
    public String getSessionId(HttpSession session) {

        return session.getId();
    }

}
