package com.chong.bys.user.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichong
 * @since 2019-01-05
 */
@TableName("t_sys_authoritie")
public class SysAuthoritie extends Model<SysAuthoritie> {

    private static final long serialVersionUID = 1L;

    /**
     * 权限资源表id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 权限名字
     */
	private String authoritieName;
    /**
     * 权限标识符
     */
	private String authoritieResource;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 权限描述
     */
	private String descirption;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthoritieName() {
		return authoritieName;
	}

	public void setAuthoritieName(String authoritieName) {
		this.authoritieName = authoritieName;
	}

	public String getAuthoritieResource() {
		return authoritieResource;
	}

	public void setAuthoritieResource(String authoritieResource) {
		this.authoritieResource = authoritieResource;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescirption() {
		return descirption;
	}

	public void setDescirption(String descirption) {
		this.descirption = descirption;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
