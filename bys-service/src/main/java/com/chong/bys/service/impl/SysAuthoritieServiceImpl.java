package com.chong.bys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chong.bys.domain.pojo.SysAuthoritie;
import com.chong.bys.dao.SysAuthoritieMapper;
import com.chong.bys.service.SysAuthoritieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichong
 * @since 2018-09-09
 */
@Service
public class SysAuthoritieServiceImpl extends ServiceImpl<SysAuthoritieMapper, SysAuthoritie> implements SysAuthoritieService {

    @Autowired
    SysAuthoritieMapper sysAuthoritieMapper;

    @Override
    public List<SysAuthoritie> selectAuthoritiesByUserId(Long id) {

        return sysAuthoritieMapper.selectAuthoritiesByUserId(id);
    }
}
