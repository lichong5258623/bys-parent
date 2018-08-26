package com.chong.bys;

import com.chong.bys.domain.pojo.SysUser;
import com.chong.bys.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {


	@Autowired
	RedisUtil redisUtil;

	@Test
	public void contextLoads() {

		SysUser sysUser = new SysUser();
		sysUser.setId((long)1);
		sysUser.setHeadImage("adsfasdf");
		sysUser.setCreateTime(new Date());

		redisUtil.setString("lichong","李崇");

		redisUtil.setObject("object",sysUser);

		SysUser object = redisUtil.getObjet("object", SysUser.class);

		log.info("值：{}",object);

	}






}
