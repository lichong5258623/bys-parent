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
@TableName("t_sys_user_role")
public class SysUserRole  {

    private static final long serialVersionUID = 1L;

    /**
     * 用户的id
     */
	private Long userId;
    /**
     * 角色的id
     */
	private Integer roleId;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
