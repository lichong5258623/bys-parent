package com.chong.bys.remote.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chong.bys.remote.service.TestRemoteService;
import org.springframework.stereotype.Component;

/**
 * @author lichong
 * 2018/9/15 22:45
 * @version 1
 * @since 1.0
 */
@Service
@Component
public class TestRemoteServiceImpl implements TestRemoteService {
    @Override
    public String getUserName(String id) {
        System.out.println("aaaaaaaaaaaaaaaaaaa");
        return "id："+id+"远程调用成功";
    }
}
