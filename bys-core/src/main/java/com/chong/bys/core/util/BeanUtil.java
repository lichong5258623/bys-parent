package com.chong.bys.core.util;

import com.chong.bys.core.exception.BysException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 对象工具类
 */
@Slf4j
public class BeanUtil {

    public static void convert(Object source,Object target){
        try {
            BeanUtils.copyProperties(target,source);
        }catch (Exception e){
            log.info("对象拷贝出错{}--->{}",source,target);
            throw new BysException("对象拷贝出错");
        }
    }

}
