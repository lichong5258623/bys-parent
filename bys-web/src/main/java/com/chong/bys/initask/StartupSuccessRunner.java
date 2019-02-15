package com.chong.bys.initask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 功能说明：项目启动完毕后执行的方法，order注解可以设置多个的启动顺序
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @since 3.0
 */
@Component
@Order(value = 1)
@Slf4j
public class StartupSuccessRunner implements CommandLineRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     */
    @Override
    public void run(String... args) {
        log.info("项目启动完毕后执行的方法一。。。。");
    }
}
