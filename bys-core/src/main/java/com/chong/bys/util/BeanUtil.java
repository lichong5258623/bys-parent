package com.chong.bys.util;

import org.springframework.beans.BeanUtils;

/**
 * 对象工具类
 */
public class BeanUtil {

    public static void convert(Object source,Object target){
        BeanUtils.copyProperties(source,target);
    }

}
