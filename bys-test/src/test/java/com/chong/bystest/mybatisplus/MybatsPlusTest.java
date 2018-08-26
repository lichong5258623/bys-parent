package com.chong.bystest.mybatisplus;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.chong.bys.domain.pojo.SysUser;
import com.chong.bys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void test01() {

        ArrayList<SysUser> sysUsers = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setCreateTime(new Date());
            sysUser.setHeadImage("image" + i);
            sysUser.setEmail(i + "banch@lichiong.com");
            sysUser.setLoginName("lichong0" + i);
            sysUser.setPassword("lichong0" + i);
            sysUser.setUserName("licihong0" + i);
            sysUsers.add(sysUser);
        }
        long startTime = System.currentTimeMillis();
        sysUserService.insertBatch(sysUsers);
        long endtime = System.currentTimeMillis();
        log.info("执行时间：{}", endtime - startTime);
    }

    @Test
    public void test02() {

        Page<SysUser> sysUserPage = new Page<>();
        EntityWrapper<SysUser> sysUserEntityWrapper = new EntityWrapper<>();
        Page<SysUser> sysUserPage1 = sysUserService.selectPage(sysUserPage);


    }


}
