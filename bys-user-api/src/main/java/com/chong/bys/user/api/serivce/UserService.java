package com.chong.bys.user.api.serivce;

import com.chong.bys.user.api.pojo.UserDto;

/**
 * 用户服务模块api
 */
public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     */
    UserDto getUserByUserName(String username);
}
