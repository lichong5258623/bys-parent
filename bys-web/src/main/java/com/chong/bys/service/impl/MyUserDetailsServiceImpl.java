package com.chong.bys.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chong.bys.domain.pojo.UserAuthoritie;
import com.chong.bys.security.BysUserVo;
import com.chong.bys.service.MyUserDetailsService;
import com.chong.bys.user.api.pojo.AuthoritieDto;
import com.chong.bys.user.api.pojo.UserDto;
import com.chong.bys.user.api.serivce.UserService;
import com.chong.bys.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lichong
 * @version 1
 * @date 2018/9/4 22:53
 * @since 1.0
 */
@Slf4j
@Component
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    @Reference
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名" + username);
        UserDto userDto = userService.getUserByUserName(username);
        if (userDto == null) {
            throw new UsernameNotFoundException("你输入的密码和账户名不匹配");
        }
        BysUserVo bysUserVo = new BysUserVo();
        BeanUtil.convert(userDto, bysUserVo);
        log.info("需要验证的用户信息：{}", bysUserVo);
        List<AuthoritieDto> authoritieDtos = userService.selectAuthoritiesByUserId(bysUserVo.getId());
        HashSet<GrantedAuthority> grantedAuthorities = authoritieDtos.stream().parallel().map(authoritieDto -> {
            UserAuthoritie userAuthoritie = new UserAuthoritie();
            BeanUtil.convert(authoritieDto, userAuthoritie);
            return userAuthoritie;
        }).collect(Collectors.toCollection(HashSet::new));
        bysUserVo.setAuthorities(grantedAuthorities);
        return bysUserVo;
    }

    /**
     * 创建用户
     *
     * @param bysUserVo
     */
    @Override
    public boolean createUser(BysUserVo bysUserVo) {
        UserDto userDto = new UserDto();
        BeanUtil.convert(bysUserVo,userDto);
        String password = userDto.getPassword();
        userDto.setPassword(passwordEncoder.encode(password));
        log.info("密码长度：{}", passwordEncoder.encode(password).length());

        return userService.save(userDto);
    }

    /**
     * 更新用户
     *
     * @param bysUserVo
     */
    @Override
    public boolean updateUser(BysUserVo bysUserVo) {

        UserDto userDto = new UserDto();
        BeanUtil.convert(bysUserVo,userDto);
        return userService.updateById(userDto);
    }

    /**
     * 修改密码
     *
     * @param newPassword
     */
    @Override
    public boolean changePassword(String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext()
                .getAuthentication();
        BysUserVo details = (BysUserVo) currentUser.getPrincipal();
        UserDto userDto = new UserDto();
        userDto.setId(details.getId());
        userDto.setPassword(passwordEncoder.encode(newPassword));
        return userService.updateById(userDto);
    }

}
