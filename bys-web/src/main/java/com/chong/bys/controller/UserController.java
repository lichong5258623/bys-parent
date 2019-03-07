package com.chong.bys.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chong.bys.artical.service.ArticalService;
import com.chong.bys.core.util.exception.BysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

/**
 * @author lichong
 * @version 1
 * @date 2018/8/15 21:30
 * @since 1.0
 */
@Slf4j
@Controller
public class UserController extends BaseController {

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private Validator validator;

    @Reference
    ArticalService articalService;

    private RequestCache requestCache = new HttpSessionRequestCache();

    @GetMapping("/index")
    public String index(Model model) {

        model.addAttribute("name", "lichong");
        model.addAttribute("hello", "hello");

        return "index";
    }

    @GetMapping("/loginPage.html")
    public String loginType(HttpServletRequest request, HttpServletResponse response) {
        //判断是否携带错误信息
        if (request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) != null) {

            log.info("错误信息：{}", request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));
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
                throw new BysException("用户未登录，请引导用户登录");
            }
        }
        return "loginPage";
    }

    @GetMapping("/getSessionId")
    @ResponseBody
    public String getLoginSessionId(HttpServletRequest request) {

        return request.getSession().getId();
    }


    /**
     * 测试手动解析thymeleaf模板为html，方便缓存整个页面
     *
     * @return
     */
    @GetMapping(value = "/testProceTemplate", produces = "text/html")
    @ResponseBody
    public String testProceTemplate(HttpServletRequest request, HttpServletResponse response, Model model) {

        String html;
        model.addAttribute("name", "李崇");
        model.addAttribute("hello", "你好，手动解析html成功");
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        ISpringTemplateEngine templateEngine = thymeleafViewResolver.getTemplateEngine();
        html = templateEngine.process("/index", webContext);
        return html;
    }

}
