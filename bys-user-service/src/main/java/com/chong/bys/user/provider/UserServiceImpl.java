package com.chong.bys.user.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chong.bys.user.api.pojo.AuthoritieDto;
import com.chong.bys.user.api.pojo.UserDto;
import com.chong.bys.user.api.serivce.UserService;
import com.chong.bys.user.domain.SysAuthoritie;
import com.chong.bys.user.domain.SysUser;
import com.chong.bys.user.service.ISysAuthoritieService;
import com.chong.bys.user.service.ISysUserService;
import com.chong.bys.core.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务提供者
 */
@Service
@Component
@Scope
public class UserServiceImpl implements UserService {

    @Autowired
    ISysUserService sysUserService;

    @Autowired
    ISysAuthoritieService sysAuthoritieService;



    @Override
    public UserDto getUserByUserName(String username) {
        QueryWrapper<SysUser> sysUserEntityWrapper = new QueryWrapper<>();
        sysUserEntityWrapper.eq("username", username);
        SysUser sysUser = sysUserService.getOne(sysUserEntityWrapper);
        UserDto userDto = new UserDto();
        BeanUtil.convert(sysUser,userDto);
        return userDto;
    }

    /**
     * 新增用户
     *
     * @param userDto
     * @return
     */
    @Override
    public boolean save(UserDto userDto) {
        SysUser sysUser = new SysUser();
        BeanUtil.convert(userDto, sysUser);
        sysUser.setCreateTime(new Date());
        sysUser.setAccountNonExpired(true);
        sysUser.setAccountNonLocked(true);
        sysUser.setCredentialsNonExpired(true);
        sysUser.setEnable(true);
        return sysUserService.save(sysUser);
    }

    /**
     * 根据id更新用户信息
     *
     * @param userDto
     * @return
     */
    @Override
    public boolean updateById(UserDto userDto) {
        SysUser sysUser = new SysUser();
        BeanUtil.convert(userDto, sysUser);
        return sysUserService.updateById(sysUser);
    }

    /**
     * 根据用户id获取用户权限列表
     *
     * @param id
     */
    @Override
    public List<AuthoritieDto> selectAuthoritiesByUserId(Long id) {
        List<SysAuthoritie> sysAuthorities = sysAuthoritieService.selectAuthoritiesByUserId(id);
        return sysAuthorities.stream().map(UserServiceImpl::convertAuthoritieDto).collect(Collectors.toList());
    }

    private static AuthoritieDto convertAuthoritieDto(SysAuthoritie sysAuthoritie) {
        AuthoritieDto authoritieDto = new AuthoritieDto();
        BeanUtil.convert(sysAuthoritie, authoritieDto);
        return authoritieDto;
    }
}
