package com.chong.bys.user.dao;

import com.chong.bys.user.domain.SysAuthoritie;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lichong
 * @since 2019-01-05
 */
public interface SysAuthoritieMapper extends BaseMapper<SysAuthoritie> {

    @Select("SELECT * FROM t_sys_authoritie where id in (SELECT authoritie_id FROM t_sys_role_authoritie WHERE role_id in (SELECT role_id FROM t_sys_user_role WHERE user_id =#{id} ))")
    List<SysAuthoritie> selectAuthoritiesByUserId(@Param("id") long id);
}