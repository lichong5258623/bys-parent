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

        log.info("发生异常：{}",exception.getMessage());
       return redirect("/error");
    }

    @ExceptionHandler(Throwable.class)
    public String Exception(Exception exception, HttpServletRequest request){

        log.info("发生异常：{}",exception.getMessage());
        return redirect("/error");
    }
}
