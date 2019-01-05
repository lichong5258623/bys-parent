package com.chong.bys.user.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chong.bys.user.api.pojo.UserDto;
import com.chong.bys.user.api.serivce.UserService;
import com.chong.bys.user.domain.SysUser;
import com.chong.bys.user.service.ISysUserService;
import com.chong.bys.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 用户服务提供者
 */
@Service
@Component
@Scope
public class UserServiceImpl implements UserService {

    @Autowired
    ISysUserService sysUserService;
    @Override
    public UserDto getUserByUserName(String username) {
        QueryWrapper<SysUser> sysUserEntityWrapper = new QueryWrapper<>();
        sysUserEntityWrapper.eq("username", username);
        SysUser sysUser = sysUserService.getOne(sysUserEntityWrapper);
        UserDto userDto = new UserDto();
        BeanUtil.convert(sysUser,userDto);
        return userDto;
    }
}
