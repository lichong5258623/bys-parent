package com.chong.bystest.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class shiroTest {


    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();


    @Before
    public void init(){

        simpleAccountRealm.addAccount("lichong","123456","admin");

    }


    @Test
    public void testAuthentication(){


        //构建serurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //获取主体对象
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("lichong", "123456");

        try {

            subject.login(token);
        }catch (UnknownAccountException e){
            log.info("用户名错误");
        }catch (IncorrectCredentialsException e){
            log.info("密码错误");
        }

        log.info("是否登陆：{}",subject.isAuthenticated());

        subject.checkRole("admin");

        subject.logout();

        log.info("是否登陆：{}",subject.isAuthenticated());
    }



    @Test
    public void customRealmTest(){


        CustomRealm customRealm = new CustomRealm();

        //构建serurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);


        //获取主体对象
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("lichong", "123456");

        try {

            subject.login(token);
        }catch (UnknownAccountException e){
            log.info("用户名错误");
        }catch (IncorrectCredentialsException e){
            log.info("密码错误");
        }

        log.info("是否登陆：{}",subject.isAuthenticated());


        subject.checkRole("admin");
        subject.checkPermission("user:delete");

        subject.logout();

        log.info("是否登陆：{}",subject.isAuthenticated());
    }

}
