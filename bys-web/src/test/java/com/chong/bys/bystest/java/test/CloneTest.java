/**************************************************************************
 * Copyright (c) 2018-2022 ZheJiang Electronic Port, Inc.
 * All rights reserved.
 *
 * 项目名称：海蛛
 * 版权说明：本软件属浙江电子口岸有限公司所有，在未获得浙江电子口岸有限公司正式授权
 *           情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *           识产权保护的内容。                            
 ***************************************************************************/
package com.chong.bys.bystest.java.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 功能说明：
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @date : 2018/11/28  10:02
 */
@Slf4j
public class CloneTest {


    @Test
    public void test01() throws CloneNotSupportedException {


        User3 user3 = new User3();
        ArrayList<User3> user3s = new ArrayList<>();
        for(int i=0;i<10;i++){
            User3 clone = (User3)user3.clone();
            boolean equals = clone.equals(user3);
            log.info(equals+"");
            clone.setId(i+"");
            user3s.add(clone);
        }

        for (User3 user31 : user3s) {
            log.info(user31.getId());
        }

    }

}
