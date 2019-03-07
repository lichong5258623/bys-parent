package com.chong.bys.user.api.serivce;

import com.chong.bys.user.api.dto.AuthoritieDto;
import com.chong.bys.user.api.dto.UserDto;

import java.util.List;

/**
 * 用户服务模块api
 */
public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param username
     */
    UserDto getUserByUserName(String username);

    /**
     * 新增用户
     * @param userDto
     * @return
     */
    boolean save(UserDto userDto);

    /**
     * 根据id更新用户信息
     * @param userDto
     * @return
     */
    boolean updateById(UserDto userDto);

    /**
     * 根据用户id获取用户权限列表
     * @param id
     */
    List<AuthoritieDto> selectAuthoritiesByUserId(Long id);
}
