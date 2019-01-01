package com.chong.bys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chong.bys.domain.pojo.SysAuthoritie;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lichong
 * @since 2018-09-09
 */
public interface SysAuthoritieMapper extends BaseMapper<SysAuthoritie> {

    @Select("SELECT * FROM t_sys_authoritie where id in (SELECT authoritie_id FROM t_sys_role_authoritie WHERE role_id in (SELECT role_id FROM t_sys_user_role WHERE user_id =#{id} ))")
    List<SysAuthoritie> selectAuthoritiesByUserId(@Param("id") long id);
}
