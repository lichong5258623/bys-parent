package com.chong.bys.bystest.java.test;

import cn.hutool.core.bean.BeanUtil;
import com.chong.bys.domain.pojo.SysUser;
import com.chong.bys.domain.vo.BysUserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lichong
 * @version 1
 * @date 2018/8/31 17:45
 * @since 1.0
 */
@Slf4j
public class beanUtilTest {

    ArrayList<User> users = new ArrayList<>(3000000);

    @Before
    public void before(){
        for (int i = 0; i < 3000000; i++) {
            User user = new User();
            user.setAddress("阿萨德发生地方" + i);
            user.setName("啊啊啊" + i);
            users.add(user);
        }
    }


    @Test
    public void test01() throws Exception {


//        OsInfo osInfo = SystemUtil.getOsInfo();
//        JavaInfo javaInfo = SystemUtil.getJavaInfo();
//        JvmInfo jvmInfo = SystemUtil.getJvmInfo();
//        log.info("结果：{}", jvmInfo);
        users.forEach(s -> s.setCreatTime(new Date()));
        ObjectMapper objectMapper = new ObjectMapper();
        long l = System.currentTimeMillis();
        //fastjsjon消耗时间：467 521 472 476 463
        //hutool工具类消耗时间：1229 1350 1281 1340
        //gson消耗时间 2204 2160 2288
        //fastjson消耗时间 522 553 538 524
        List<String> collect = users.stream().parallel().map(s -> {
            try {
                return objectMapper.writeValueAsString(s);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        long l1 = System.currentTimeMillis();

        long start = System.currentTimeMillis();
        //fastjsjon消耗时间：1707 1683 1720 1621 1644
        //hutool工具类消耗时间：7950 8309 8190 7965
        //gson消耗时间 10754 10259 10334
        //fastjson消耗时间 1967 2079 1990 2000
        List<User> collect1 = collect.stream().map(s -> {
            try {
                return objectMapper.readValue(s, User.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        log.info("对象转json消耗时间：{}", l1 - l);
        log.info("json转对象消耗时间：{}", end - start);
        log.info("结果：{}", collect.get(23));
        log.info("结果：{}", collect1.get(23).getCreatTime());
    }


    @Test
    public void test03(){

        users.forEach(s -> s.setCreatTime(new Date()));

        long l = System.currentTimeMillis();
        //hutool工具类消耗时间：1229 1350 1281 1340
        List<Map<String,Object>> collect = users.stream().parallel().map(BeanUtil::beanToMap).collect(Collectors.toList());

        long l1 = System.currentTimeMillis();

        log.info("对象转map消耗时间：{}", l1 - l);
        long start = System.currentTimeMillis();
        //hutool工具类消耗时间：7950 8309 8190 7965
        List<User> collect1 = collect.stream().map(s -> BeanUtil.mapToBean(s, User.class,false)).collect(Collectors.toList());
        long end = System.currentTimeMillis();


        log.info("map转对象消耗时间：{}", end - start);

        log.info("结果：{}", collect.get(23));
        log.info("结果：{},{}", collect1.get(11111).getCreatTime(),collect1.size());
    }


    @Test
    public void test05() throws InvocationTargetException, IllegalAccessException {

        SysUser sysUser = new SysUser();
        BysUserVo bysUserVo = new BysUserVo();

        bysUserVo.setCredentialsNonExpired(true);
        long l = System.currentTimeMillis();

        BeanUtils.copyProperties( bysUserVo,sysUser);

        long l1 = System.currentTimeMillis();
        log.info("时间：{}",l1-l);
        log.info("sysUser:{}",sysUser);
    }

    @Test
    public void testConvert() throws InvocationTargetException, IllegalAccessException {


        User user = new User();
        user.setId(111L);



    }

}
