package com.chong.bys.bystest.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class ConcurrentTest {


    @Test
    public void testCallable() {

        MyCallable myCallable = new MyCallable();
        List<Integer> call = null;
        try {
            call = myCallable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Integer integer : call) {

            log.info("输出:{}", integer);
        }
    }

    @Test
    public void test2123123() throws ExecutionException, InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(100);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        Executors.newCachedThreadPool();
        executorService.submit(() -> {
            log.info("记录日志....4");
        });
        executorService.submit(() -> log.info("记录日志....1"));
        executorService.submit(() -> log.info("记录日志....2"));
        executorService.submit(() -> log.info("记录日志....3"));

        for (int i =0;i<100;i++){

            LogInfo logInfo = new LogInfo(i+"");
            executorService.submit(logInfo);
            countDownLatch.countDown();
        }

        Future<String> submit = executorService.submit(() -> {
            Thread.sleep(3000);
            return "success";
        });

        String s = submit.get();
        countDownLatch.await();
        log.info(s);
        executorService.shutdown();
    }

    @Test
    public void trestasdfasdf() throws InterruptedException {

        LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>();
        strings.put("a");
        strings.put("b");
        strings.put("c");
        strings.put("d");
        strings.put("e");
        strings.put("f");
        strings.put("g");

        for(int i=0;i<strings.size();i++){

            String poll = strings.poll();

            log.info(poll);
        }

    }


}
