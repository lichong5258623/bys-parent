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

import java.io.*;

/**
 * 功能说明：
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @date : 2018/11/16  16:29
 */
@Slf4j
public class FileTest {

    @Test
    public void test() {
        BufferedInputStream fileInputStream = null;


        try {
            fileInputStream = new BufferedInputStream(new FileInputStream("D:\\work\\orders.tbl"));
            InputStreamReader in = new InputStreamReader(new FileInputStream("D:\\work\\lineitem.tbl"));
            BufferedReader br = new BufferedReader(in);

//            HashMap<String, String> stringStringHashMap = new HashMap<>(7500000);
//            byte[] bytes = new byte[2048];
//            int n = -1;
//            while (n==fileInputStream.read(bytes,0,bytes.length)) {
//                String s = new String(bytes);
//            }

//            for (int i = 0; i < 10; i++) {
//                String s = br.readLine();
//                log.info(s);
//            }
            long l = System.currentTimeMillis();
            String s;
            int count = 0;
            while ((s = br.readLine()) != null) {
//                stringStringHashMap.put(s.split("|")[0], s);
                count++;
            }

            long l1 = System.currentTimeMillis();

            log.debug("-------------总条数-----------:  ：{}", count);
            log.debug("-------------消耗时间-----------:  ：{}", l1 - l);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
