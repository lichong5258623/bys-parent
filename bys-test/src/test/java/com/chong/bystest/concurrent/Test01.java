package com.chong.bystest.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 功能说明：
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @since 3.0
 */
public class Test01 {


    private CountDownLatch countDownLatch = new CountDownLatch(2);


    @Test
    public void test01() throws InterruptedException {

        new Thread(() -> {
            try {
                System.out.println("执行子线程");
                Thread.sleep(3000);
                countDownLatch.countDown();
                System.out.println("子线程执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        countDownLatch.countDown();
        System.out.println("主线程任务执行完毕");
        System.out.println("进入等待");
        countDownLatch.await();
        System.out.println("全部任务执行完毕");
    }
}
