package com.chong.bys.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DataSourceConfig {


    private Map<Object, Object> dataSources = new HashMap<>();

    /**
     * 主库的key
     */
    private String master;

    @Bean
    public DataSource dataSource(){

        initDataSource();

        AbstractRoutingDataSource routingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                if(DynamicDataSourceHolder.getCurrentDb().equals(DynamicDataSourceHolder.MASTER)){
                    return master;
                }
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
        masterDataSource.setUrl("");
        masterDataSource.setUsername("");
        masterDataSource.setPassword("");
        dataSources.put("master",masterDataSource);
        this.master="master";
        //配置从库
        DruidDataSource slaveDataSource = new DruidDataSource();
        slaveDataSource.setUrl("");
        slaveDataSource.setUsername("");
        slaveDataSource.setPassword("");
        dataSources.put("slave",masterDataSource);
    }

}
