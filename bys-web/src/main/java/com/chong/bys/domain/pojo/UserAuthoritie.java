package com.chong.bys.domain.pojo;

import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichong
 * @since 2018-09-09
 */
public class UserAuthoritie implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "SysAuthoritie{" +
        ", id=" + id +
        ", authoritieName=" + authoritieName +
        ", authoritieResource=" + authoritieResource +
        ", createTime=" + createTime +
        ", descirption=" + descirption +
        "}";
    }

    /**
     * If the <code>GrantedAuthority</code> can be represented as a <code>String</code>
     * and that <code>String</code> is sufficient in precision to be relied upon for an
     * access control decision by an {@link } (or delegate), this
     * method should return such a <code>String</code>.
     * <p>
     * If the <code>GrantedAuthority</code> cannot be expressed with sufficient precision
     * as a <code>String</code>, <code>null</code> should be returned. Returning
     * <code>null</code> will require an <code>AccessDecisionManager</code> (or delegate)
     * to specifically support the <code>GrantedAuthority</code> implementation, so
     * returning <code>null</code> should be avoided unless actually required.
     *
     * @return a representation of the granted authority (or <code>null</code> if the
     * granted authority cannot be expressed as a <code>String</code> with sufficient
     * precision).
     */
    @Override
    public String getAuthority() {
        return authoritieResource;
    }
}
