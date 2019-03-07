package com.chong.bys.security;

import com.chong.bys.core.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理器
 *
 * @author lichong
 */
@Slf4j
@Component("bysAuthenticationSuccessHandler")
public class BysAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    JsonUtil jsonUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("登录成功");
        String accept = request.getHeader("accept");
        log.info("请求头返回类型：{}", accept);
        //判断是否是浏览器
        if (accept == null || !accept.contains("text/html")) {
            response.setContentType("application/json;charset=UTF-8");
            String str = jsonUtil.BeanToJson(authentication);
            log.info("登陆成功后的对象：{}", str);
            response.getWriter().write(str);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
