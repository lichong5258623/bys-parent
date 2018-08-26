package com.chong.bystest.concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志对象
 *
 * @author li
 * @date 2018/4/25 0025下午 3:31
 */
@Slf4j
public class LogInfo implements Runnable {


    private String msg;

    public LogInfo(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {

        log.info("执行任务: {}",msg);

    }
}
