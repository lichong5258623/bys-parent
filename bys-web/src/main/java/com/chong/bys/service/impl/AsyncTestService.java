package com.chong.bys.service.impl;

import com.chong.bys.core.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author lichong
 * @version 1
 * @date 2018/8/22 21:51
 * @since 1.0
 */
@Slf4j
@Service
public class AsyncTestService {


    @Autowired
    RedisUtil redisUtil;

    @Async
    public void asyncService01() throws InterruptedException {

        log.info("先睡三秒");
        Thread.sleep(3000);

        log.info("睡醒了");
    }

    public void syncService01() throws InterruptedException {

        log.info("先睡三秒");
        Thread.sleep(3000);

        log.info("睡醒了");
    }

}
