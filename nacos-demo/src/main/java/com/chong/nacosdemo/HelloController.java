/**************************************************************************
 * Copyright (c) 2018-2022 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 *
 * 项目名称：海蛛
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.chong.nacosdemo;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 功能说明：
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @date : 2018/11/12  16:40
 */
@RestController
public class HelloController {


    @Value("${author.name}")
    private String useLocalCache;

    @NacosInjected
    private NamingService namingService;

    @GetMapping(value = "/get")
    public String get() {
        return useLocalCache;
    }

    @RequestMapping(value = "/get2", method = GET)
    public List<Instance> get2(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    public void setUseLocalCache(String useLocalCache) {
        this.useLocalCache = useLocalCache;
    }
}
