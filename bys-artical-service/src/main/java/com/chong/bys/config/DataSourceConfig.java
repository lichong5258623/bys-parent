package com.chong.bys.config;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Configuration
public class DataSourceConfig {


    private Map<Object, Object> dataSources = new HashMap<>();

    /**从库访问计数器*/
    private AtomicInteger slaveCounter = new AtomicInteger(-1);

    /**
     * 主库的key
     */
    private Object master;

    /**
     * 从库的key
     */
    private ArrayList<Object> slaves = new ArrayList<>();


    @Bean
    @Lazy
    public DataSource dataSource() {
        initDataSource();
        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                if (DynamicDataSourceHolder.useSlave() != null && DynamicDataSourceHolder.useSlave()) {
                    Object o = loadSlaveTarget();
                    log.info("使用：{}数据库", o);
                    return o;
                }
                log.info("使用：{}数据库", master);
                return master;
            }
        };
        routingDataSource.setTargetDataSources(dataSources);
        return routingDataSource;
    }

    /**
     * todo 后期优化到配置文件
     * 初始化dataSources,并指定主库
     */
    private void initDataSource() {

        log.info("初始化数据库");
        //配置主库
        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setUrl("jdbc:mysql://47.101.68.18:23306/springboot_test?characterEncoding=utf8&useSSL=false");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("123456");
        dataSources.put("master", masterDataSource);
        this.master = "master";
        //配置从库
        DruidDataSource slaveDataSource = new DruidDataSource();
        slaveDataSource.setUrl("jdbc:mysql://47.101.68.18:23306/springboot_test?characterEncoding=utf8&useSSL=false");
        slaveDataSource.setUsername("root");
        slaveDataSource.setPassword("123456");
        dataSources.put("slave", slaveDataSource);
        slaves.add("slave");
        DruidDataSource slaveDataSource2 = new DruidDataSource();
        slaveDataSource2.setUrl("jdbc:mysql://47.101.68.18:23306/springboot_test?characterEncoding=utf8&useSSL=false");
        slaveDataSource2.setUsername("root");
        slaveDataSource2.setPassword("123456");
        dataSources.put("slave2", slaveDataSource);
        slaves.add("slave2");
    }

    private Object loadSlaveTarget() {
        int slave = slaveCounter.incrementAndGet() % slaves.size();
        if (slaveCounter.get() == Integer.MAX_VALUE) {
            slaveCounter.set(-1);
        }
        log.info(slaveCounter+"");
        return slaves.get(slave);
    }

}
