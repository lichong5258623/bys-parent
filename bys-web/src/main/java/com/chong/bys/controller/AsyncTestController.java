package com.chong.bys.controller;

import com.chong.bys.service.impl.AsyncTestService;
import com.chong.bys.core.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author lichong
 * @version 1
 * @date 2018/8/22 21:55
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/asynctest")
public class AsyncTestController {

    @Autowired
    AsyncTestService asyncTestService;

    @Autowired
    RedisUtil redisUtil;



    @GetMapping("/async01")
    public String testAsync01() throws InterruptedException {

        asyncTestService.asyncService01();

        return "异步执行结束没有返回值";
    }

    @GetMapping("/async02")
    public String testAsync02() throws InterruptedException {

        asyncTestService.syncService01();
        return "同步执行结束没有返回值";
    }

    @GetMapping("/getAll")
    public DeferredResult testAsync03(){
        DeferredResult deferredResult = new DeferredResult((long) 30000);

        log.info("我是{}",Thread.currentThread().getId());
        redisUtil.setObject("deferred001",deferredResult);
        return deferredResult;
    }
}
