package com.chong.bys.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  redis配置参数类
 * @author lichong
 * @since 2018/02/04 22:54
 */
@Component
@ConfigurationProperties(prefix="redis")
public class MyRedisProperties {
	private String host;
	private int port;
	/**
	 *单位： 秒
	 */
	private int timeout;
	private String password;
	private int poolMaxTotal;
	private int poolMaxIdle;
	/**
	 * 单位：秒
	 */
	private int poolMaxWait;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoolMaxTotal() {
		return poolMaxTotal;
	}
	public void setPoolMaxTotal(int poolMaxTotal) {
		this.poolMaxTotal = poolMaxTotal;
	}
	public int getPoolMaxIdle() {
		return poolMaxIdle;
	}
	public void setPoolMaxIdle(int poolMaxIdle) {
		this.poolMaxIdle = poolMaxIdle;
	}
	public int getPoolMaxWait() {
		return poolMaxWait;
	}
	public void setPoolMaxWait(int poolMaxWait) {
		this.poolMaxWait = poolMaxWait;
	}
}
