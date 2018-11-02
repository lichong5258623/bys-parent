package com.chong.bys.mybatisplus;

import com.chong.bys.domain.pojo.SysUser;
import com.chong.bys.domain.vo.BysUserVo;
import com.chong.bys.service.MyUserDetailsService;
import com.chong.bys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author lichong
 * @version 1
 * @date 2018/8/24 21:31
 * @since 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatsPlusTest {


    @Autowired
    SysUserService sysUserService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test01() {

        ArrayList<SysUser> sysUsers = new ArrayList<>(1000);
        String encode = passwordEncoder.encode("123456");
        for (int i = 0; i < 1000; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setCreateTime(new Date());
            sysUser.setLastLoginTime(new Date());
            sysUser.setHeadImage("image" + i);
            sysUser.setEmail(i + "banch@lichong.com");
            sysUser.setUsername("lichong0" + i);
            sysUser.setPassword(encode);
            sysUser.setName("lichong" + i);
            sysUser.setPhone("1111111"+i);
            sysUsers.add(sysUser);
        }
        long startTime = System.currentTimeMillis();
        sysUserService.saveBatch(sysUsers);
        long endtime = System.currentTimeMillis();
        log.info("执行时间：{}", endtime - startTime);
    }



    @Test
    public void  test03(){

        for (int i =0;i<1000;i++){

            BysUserVo bysUserVo = new BysUserVo();
            bysUserVo.setCreateTime(new Date());
            bysUserVo.setLastLoginTime(new Date());
            bysUserVo.setHeadImage("image" + i);
            bysUserVo.setEmail(i + "banch@lichiong.com");
            bysUserVo.setUsername("lichong0" + i);
            bysUserVo.setPassword("111111");
            bysUserVo.setName("licihong0" + i);
            bysUserVo.setPhone("1111111"+i);

            myUserDetailsService.createUser(bysUserVo);
        }
    }

    @Test
    public void test04(){

//        EntityWrapper<SysUser> sysUserEntityWrapper = new EntityWrapper<>();
//        sysUserEntityWrapper.eq("username", "lichong00");
//        SysUser sysUser = sysUserService.selectOne(sysUserEntityWrapper);

        boolean matches = passwordEncoder.matches("111111", "$2a$10$Tyjkeh39jBKP7J64Jz/UB.rRpkNDQVT79oQ//NhJ2qweSyNp4H73O");

        System.out.println(matches);

    }

}
