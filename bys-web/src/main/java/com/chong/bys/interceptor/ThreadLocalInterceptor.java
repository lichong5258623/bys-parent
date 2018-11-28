package com.chong.bys.interceptor;

import com.chong.bys.base.BysLocalMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 清理本地线程绑定数据
 * @author lichong
 */
@Slf4j
public class ThreadLocalInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle------请求前处理...");

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("posthandle------请求结束后处理...");

    }


    /**
     * controller和视图渲染之后执行，主要用于清理资源例如ThreadLocal
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清理threadlocal
        BysLocalMsg.removeUser();
        log.info("清理用户数据");
    }
}
