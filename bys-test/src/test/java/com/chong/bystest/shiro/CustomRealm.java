package com.chong.bystest.shiro;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
public class CustomRealm extends AuthorizingRealm {


    private Map<String,String> map = new HashMap(16);


    {
        map.put("lichong","00bb8ad800864f894b5524b3123e3a08");
        super.setName("customRealm");
    }

    /**
     * 授权
     * Retrieves the AuthorizationInfo for the given principals from the underlying data store.  When returning
     * an instance from this method, you might want to consider using an instance of
     * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String userName = (String)principals.getPrimaryPrincipal();
        //获取角色信息
        Set<String> roles = getRolesByUserName(userName);
        //获取权限信息
        Set<String> permissiones = getPermissionsByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissiones);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionsByUserName(String userName) {

        HashSet<String> permission = Sets.newHashSet();
        permission.add("user:add");
        permission.add("user:delete");
        permission.add("user:update");
        return permission;
    }

    private Set<String> getRolesByUserName(String userName) {
        HashSet<String> roles = Sets.newHashSet();
        roles.add("admin");
        roles.add("user");
        return roles;
    }

    /**
     * 认证
     * Retrieves authentication data from an implementation-specific datasource (RDBMS, LDAP, etc) for the given
     * authentication token.
     * <p/>
     * For most datasources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param token the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        String userName = (String) token.getPrincipal();
        String password = getPasswordByUserName(userName);

        if(password==null){

            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, password, "CustomRealm");

        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("customsalt"));
        return simpleAuthenticationInfo;

    }

    private String getPasswordByUserName(String userName) {


        return map.get(userName);

    }


    public static void main(String[] args) {

        Md5Hash customsalt = new Md5Hash("123456", "customsalt");

        log.info("加密之后的密码：{}",customsalt.toString());
    }

}
