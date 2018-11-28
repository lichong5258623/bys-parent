package com.chong.bys.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAttrbutes extends DefaultErrorAttributes {


    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        //取出自定义错误信息
        Object customErrorMsg = webRequest.getAttribute("customErrorMsg", RequestAttributes.SCOPE_REQUEST);
        //在此可以添加自定义返回的错误属性
        errorAttributes.put("author", "bys-boy");
        errorAttributes.put("customErrorMsg", customErrorMsg);
        return errorAttributes;
    }
}
