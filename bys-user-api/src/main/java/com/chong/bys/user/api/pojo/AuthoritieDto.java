package com.chong.bys.user.api.pojo;

import java.util.Date;

public class AuthoritieDto {

    /**
     * 权限资源表id
     */
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
}
