package com.chong.bys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chong.bys.domain.pojo.SysAuthoritie;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lichong
 * @since 2018-09-09
 */
public interface SysAuthoritieService extends IService<SysAuthoritie> {

    List<SysAuthoritie> selectAuthoritiesByUserId(Long id);
}
