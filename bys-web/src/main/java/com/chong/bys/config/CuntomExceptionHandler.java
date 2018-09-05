package com.chong.bys.config;

import com.chong.bys.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常控制处理器
 * @author lichong
 */
@Slf4j
@ControllerAdvice
public class CuntomExceptionHandler extends BaseController {


    @ExceptionHandler(RuntimeException.class)
    public String runtimeException(RuntimeException exception, HttpServletRequest request){
        //设置请求状态码
        request.setAttribute("javax.servlet.error.status_code",500);
        //放置自定义错误信息
        request.setAttribute("customErrorMsg", "错误信息");
        log.info("发生异常：{}",exception.getMessage());
        return "forward:/error";
    }

    @ExceptionHandler(Throwable.class)
    public String Exception(Exception exception, HttpServletRequest request){

        log.info("发生异常：{}",exception.getMessage());
        return "forward:/error";
    }
}
