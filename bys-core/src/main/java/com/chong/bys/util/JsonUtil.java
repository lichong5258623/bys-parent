package com.chong.bys.util;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    /**
     * 实体转成Json格式字符串
     * @param object
     * @return
     */
    public static String BeanToJson(Object object){
        return  JSON.toJSONString(object);
    }

    /**
     * 把字符串转对应的Bean
     * @param jsonStr
     * @param clazz
     * @param <T> 需要的bean的格式
     * @return
     */
    public static <T>T JsonToBean(String jsonStr,Class<T> clazz){

        return JSON.parseObject(jsonStr, clazz);
    }

}
