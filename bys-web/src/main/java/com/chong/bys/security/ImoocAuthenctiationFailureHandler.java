package com.chong.bys.security;

import com.chong.bys.exception.LoginFailureException;
import com.chong.bys.exception.NoLoginException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 此类是登录失败后的处理器
 * @author lichong
 */
@Slf4j
@Component("imoocAuthenctiationFailureHandler")
public class ImoocAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");
        String accept = request.getHeader("accept");
        log.info("请求头返回类型：{}", accept);
        //判断是否是浏览器
        if (accept == null || !accept.contains("text/html")) {
            //不是浏览器抛出异常系统自动封装成json返回前台
            throw new BadCredentialsException("密码错误");
        } else {
            //设置失败使用转发携带错误信息
            // setUseForward(true);
            //查看源码可知 若开启转发，错误信息会放到request里面，否则放置到session里面，源码在此类继承的父类中的saveException()方法中
            //这里使用重定向防止url中显示登陆地址
            setDefaultFailureUrl("/loginPage.html");
            //重写异常，返回给前台显示
            super.onAuthenticationFailure(request, response, new BadCredentialsException("密码错误"));
        }
    }
}