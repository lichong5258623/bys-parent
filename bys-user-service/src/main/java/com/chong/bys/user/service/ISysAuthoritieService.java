package com.chong.bys.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chong.bys.user.domain.SysAuthoritie;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lichong
 * @since 2019-01-05
 */
public interface ISysAuthoritieService extends IService<SysAuthoritie> {

    List<SysAuthoritie> selectAuthoritiesByUserId(long id);
}
