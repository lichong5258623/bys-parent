package com.chong.bys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chong.bys.domain.pojo.SysAuthoritie;
import com.chong.bys.domain.pojo.SysUser;
import com.chong.bys.domain.vo.BysUserVo;
import com.chong.bys.service.MyUserDetailsService;
import com.chong.bys.service.SysAuthoritieService;
import com.chong.bys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

/**
 * @author lichong
 * @version 1
 * @date 2018/9/4 22:53
 * @since 1.0
 */
@Slf4j
@Component
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SysAuthoritieService sysAuthoritieService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名" + username);
        QueryWrapper<SysUser> sysUserEntityWrapper = new QueryWrapper<>();
        sysUserEntityWrapper.eq("username", username);
        SysUser sysUser = sysUserService.getOne(sysUserEntityWrapper);
        if (sysUser == null) {
            throw new UsernameNotFoundException("你输入的密码和账户名不匹配");
        }
        BysUserVo bysUserVo = convertToBysUserVo(sysUser);
        log.info("需要验证的用户信息：{}", bysUserVo);
        List<SysAuthoritie> sysAuthorities = sysAuthoritieService.selectAuthoritiesByUserId(bysUserVo.getId());
        HashSet<GrantedAuthority> grantedAuthorities = new HashSet<>(sysAuthorities);
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
        SysUser sysUser = bysUserVo.convertToSysUser();
        String password = sysUser.getPassword();
        sysUser.setPassword(passwordEncoder.encode(password));
        sysUser.setAccountNonExpired(1);
        sysUser.setEnable(1);
        sysUser.setAccountNonLocked(1);
        sysUser.setCredentialsNonExpired(1);
        log.info("密码长度：{}", passwordEncoder.encode(password).length());

        return sysUserService.save(sysUser);
    }

    /**
     * 更新用户
     *
     * @param bysUserVo
     */
    @Override
    public boolean updateUser(BysUserVo bysUserVo) {

        SysUser sysUser = bysUserVo.convertToSysUser();
        return sysUserService.updateById(sysUser);
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

        SysUser sysUser = new SysUser();
        sysUser.setId(details.getId());
        sysUser.setPassword(passwordEncoder.encode(newPassword));

        return sysUserService.updateById(sysUser);
    }


    private BysUserVo convertToBysUserVo(SysUser sysUser) {
        BysUserVo bysUserVo = new BysUserVo();
        BeanUtils.copyProperties(sysUser, bysUserVo);
        bysUserVo.setAccountNonExpired(convertState(sysUser.getAccountNonExpired()));
        bysUserVo.setAccountNonLocked(convertState(sysUser.getAccountNonLocked()));
        bysUserVo.setEnabled(convertState(sysUser.getEnable()));
        bysUserVo.setCredentialsNonExpired(convertState(sysUser.getCredentialsNonExpired()));
        return bysUserVo;
    }

    private boolean convertState(int state) {
        return state == 1;
    }

}