package com.chong.bys.commentary.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.chong.bys.core.config.DbInfo;
import com.chong.bys.core.config.DynamicDataSourceHolder;
import com.chong.bys.core.config.DynamicDataSourceProperties;
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
                //没有从库
                if (slaves.size() < 1) {
                    log.info("使用：{}数据库", master);
                    return master;
                }
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
        DruidDataSource masterDataSource = getDataSource(master);
        if (master.getDataSourceKey() != null) {
            this.master = master.getDataSourceKey();
        } else {
            this.master = MASTER_DATASOURCE_KEY +"@"+ master.getUrl().split("//")[1].split("/")[0];
        }
        dataSources.put(this.master, masterDataSource);
        log.info("加载主数据源：{} 完毕", this.master);
        if (dynamicDataSourceProperties.getSlaves() == null || dynamicDataSourceProperties.getSlaves().size() < 1) {
            log.info("未配置slave数据源，即本项目只有一个数据源");
            return;
        }
        log.info("初始化salve数据源");
        for (DbInfo slave : dynamicDataSourceProperties.getSlaves()) {
            if (slave.getDataSourceKey() == null) {
                String s = slave.getUrl().split("//")[1].split("/")[0];
                slave.setDataSourceKey("slave@" + s);
            }
            DruidDataSource slaveDataSource = getDataSource(slave);
            dataSources.put(slave.getDataSourceKey(), slaveDataSource);
            slaves.add(slave.getDataSourceKey());
            log.info("salve数据源：{} 加载完毕", slave.getDataSourceKey());
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
        log.info("slave查询次数：{}", slaveCounter);
        return slaves.get(slave);
    }

    /**
     * 获取datasource
     * @param dbInfo 数据库信息
     * @return datasource
     * @throws SQLException
     */
    private DruidDataSource getDataSource(DbInfo dbInfo) throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dbInfo.getUrl());
        dataSource.setUsername(dbInfo.getUsername());
        dataSource.setPassword(dbInfo.getPassword());
        dataSource.setFilters("stat,wall");
        dataSource.setInitialSize(dynamicDataSourceProperties.getInitialSize());
        dataSource.setMaxActive(dynamicDataSourceProperties.getMaxActive());
        dataSource.setValidationQuery(dynamicDataSourceProperties.getValidationQuery());
        return dataSource;
    }


}
