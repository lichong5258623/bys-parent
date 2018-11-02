package com.chong.bys.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichong
 * @since 2018-09-09
 */
@TableName("t_sys_role_authoritie")
public class SysRoleAuthoritie extends Model<SysRoleAuthoritie> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 资源id
     */
    @TableField("authoritie_id")
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

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysRoleAuthoritie{" +
        ", roleId=" + roleId +
        ", authoritieId=" + authoritieId +
        "}";
    }
}
