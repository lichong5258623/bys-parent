package com.chong.bys.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.chong.bys.exception.BysException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能说明：多数据源配置
 *
 * @author lichong
 */
@Slf4j
@Configuration
public class DataSourceConfig {


    @Autowired
    private DynamicDataSourceProperties dynamicDataSourceProperties;
    private static final String MASTER_DATASOURCE_KEY = "master";
    /**
     * 所有的数据库
     */
    private Map<Object, Object> dataSources = new HashMap<>();

    /**
     * 从库访问计数器
     */
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
    public DataSource dataSource() throws SQLException {
        //初始化所有数据源
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
     * 关联配置文件
     *
     * @see DynamicDataSourceProperties
     * 初始化dataSources,并指定主库
     */
    private void initDataSource() throws SQLException {
        DbInfo master = dynamicDataSourceProperties.getMaster();
        log.info("开始初始化数据源");
        log.info("初始化master数据源");
        //配置主库
        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setUrl(master.getUrl());
        masterDataSource.setUsername(master.getUsername());
        masterDataSource.setPassword(master.getPassword());
        masterDataSource.setFilters("stat,wall");
        masterDataSource.setInitialSize(dynamicDataSourceProperties.getInitialSize());
        masterDataSource.setMaxActive(dynamicDataSourceProperties.getMaxActive());
        masterDataSource.setValidationQuery(dynamicDataSourceProperties.getValidationQuery());
        if (master.getDataSourceKey() != null) {
            this.master = master.getDataSourceKey();
        }else {
            this.master = MASTER_DATASOURCE_KEY;
        }
        dataSources.put(this.master, masterDataSource);
        log.info("加载主数据源：{} 完毕", this.master);
        log.info("初始化salve数据源");
        for (DbInfo slave : dynamicDataSourceProperties.getSlaves()) {
            if (slave.getDataSourceKey() == null) {
                throw new BysException("slave数据库dataSourceKey不能为空");
            }
            DruidDataSource slaveDataSource = new DruidDataSource();
            slaveDataSource.setUrl(slave.getUrl());
            slaveDataSource.setUsername(slave.getUsername());
            slaveDataSource.setPassword(slave.getPassword());
            slaveDataSource.setFilters("stat,wall");
            masterDataSource.setInitialSize(dynamicDataSourceProperties.getInitialSize());
            masterDataSource.setMaxActive(dynamicDataSourceProperties.getMaxActive());
            slaveDataSource.setValidationQuery(dynamicDataSourceProperties.getValidationQuery());
            dataSources.put(slave.getDataSourceKey(), slaveDataSource);
            slaves.add(slave.getDataSourceKey());
            log.info("salve数据源：{} 加载完毕",slave.getDataSourceKey());
        }
    }

    /**
     * 从库轮询算法使用线程安全的计数器
     *
     * @return 从库的key
     */
    private Object loadSlaveTarget() {
        int slave = slaveCounter.incrementAndGet() % slaves.size();
        if (slaveCounter.get() == Integer.MAX_VALUE) {
            slaveCounter.set(-1);
        }
        log.info("slave查询次数：{}",slaveCounter);
        return slaves.get(slave);
    }

}
