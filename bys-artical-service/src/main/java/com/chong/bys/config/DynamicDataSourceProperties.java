package com.chong.bys.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 功能说明：多数据源配置类
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @since 3.0
 */
@Data
@ConfigurationProperties(prefix = "bys.datasource")
@Component
public class DynamicDataSourceProperties{

    private int initialSize;
    private int maxActive;
    private long maxWait;
    private String validationQuery;
    private DbInfo master;
    private List<DbInfo> slaves;


}
