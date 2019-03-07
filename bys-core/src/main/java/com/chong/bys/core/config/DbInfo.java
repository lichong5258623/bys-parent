package com.chong.bys.core.config;

import lombok.Data;

/**
 * 功能说明：
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @since 3.0
 */
@Data
public class DbInfo {

    private String dataSourceKey;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
