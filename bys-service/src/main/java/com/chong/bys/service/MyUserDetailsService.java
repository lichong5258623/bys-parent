package com.chong.bys.service;

import com.chong.bys.domain.vo.BysUserVo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 定义
 * @author lichong
 * @version 1
 * @date 2018/9/4 22:53
 * @since 1.0
 */
public interface MyUserDetailsService extends UserDetailsService {

    /**
     * 创建用户
     * @param bysUserVo
     */
     boolean createUser(BysUserVo bysUserVo);

    /**
     * 更新用户
     * @param bysUserVo
     */
     boolean updateUser(BysUserVo bysUserVo);

    /**
     * 修改密码
     * @param newPassword
     */
    boolean changePassword(String newPassword);

}
