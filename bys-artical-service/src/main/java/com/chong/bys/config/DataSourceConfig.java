package com.chong.bys.config;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class DataSourceConfig {


    private Map<Object, Object> dataSources = new HashMap<>();

    /**
     * 主库的key
     */
    private String master;

    @Bean
    @Lazy
    public DataSource dataSource(){

        initDataSource();

        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                if(DynamicDataSourceHolder.getCurrentDb().equals(DynamicDataSourceHolder.MASTER)){
                    log.info("使用：{}数据库",master);
                    return master;
                }
                log.info("使用：{}数据库",DynamicDataSourceHolder.getCurrentDb());
                return DynamicDataSourceHolder.getCurrentDb();
            }
        };
        routingDataSource.setTargetDataSources(dataSources);
        return routingDataSource;
    }

    /**
     * 初始化dataSources,并指定主库（后期优化到配置文件）
     */
    private void initDataSource() {
        //配置主库
        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setUrl("jdbc:mysql://47.101.68.18:23306/springboot_test?characterEncoding=utf8&useSSL=false");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("123456");
        dataSources.put("master",masterDataSource);
        this.master="master";
        //配置从库
        DruidDataSource slaveDataSource = new DruidDataSource();
        masterDataSource.setUrl("jdbc:mysql://47.101.68.18:23306/springboot_test?characterEncoding=utf8&useSSL=false");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("123456");
        dataSources.put("slave",slaveDataSource);
    }

}
