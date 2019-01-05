package com.chong.bys.user.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichong
 * @since 2019-01-05
 */
@TableName("t_sys_role_authoritie")
public class SysRoleAuthoritie {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
	private Integer roleId;
    /**
     * 资源id
     */
	private Integer authoritieId;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAuthoritieId() {
		return authoritieId;
	}

	public void setAuthoritieId(Integer authoritieId) {
		this.authoritieId = authoritieId;
	}

}
