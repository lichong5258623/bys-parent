package com.chong.bys.interceptor;

import com.chong.bys.security.BysUserVo;

/**
 * 功能说明：定义一些threadLocal类
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @date : 2018/11/15  17:38
 */
public class BysLocalMsg {

    private static ThreadLocal<BysUserVo> localUser = new ThreadLocal<>();

    public static BysUserVo getUser() {
        return localUser.get();
    }

    public static void setUser(BysUserVo user) {
        localUser.set(user);
    }

    public static void removeUser() {
        localUser.remove();
    }


}
