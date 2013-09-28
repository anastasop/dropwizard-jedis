package com.github.anastasop.dropwizard.jedis;

import redis.clients.jedis.JedisPoolConfig;

public class JedisConfiguration {
	JedisPoolConfig poolConfig = new JedisPoolConfig();
	String host;
	int port;

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

	public int getMaxIdle() {
		return poolConfig.getMaxIdle();
	}

	public void setMaxIdle(int maxIdle) {
		poolConfig.setMaxIdle(maxIdle);
	}

	public int getMinIdle() {
		return poolConfig.getMinIdle();
	}

	public void setMinIdle(int minIdle) {
		poolConfig.setMinIdle(minIdle);
	}

	public int getMaxActive() {
		return poolConfig.getMaxActive();
	}

	public void setMaxActive(int maxActive) {
		poolConfig.setMaxActive(maxActive);
	}

	public long getMaxWait() {
		return poolConfig.getMaxWait();
	}

	public void setMaxWait(long maxWait) {
		poolConfig.setMaxWait(maxWait);
	}

	public byte getWhenExhaustedAction() {
		return poolConfig.getWhenExhaustedAction();
	}

	public void setWhenExhaustedAction(byte whenExhaustedAction) {
		poolConfig.setWhenExhaustedAction(whenExhaustedAction);
	}

	public boolean isTestOnBorrow() {
		return poolConfig.isTestOnBorrow();
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		poolConfig.setTestOnBorrow(testOnBorrow);
	}

	public boolean isTestOnReturn() {
		return poolConfig.isTestOnReturn();
	}

	public void setTestOnReturn(boolean testOnReturn) {
		poolConfig.setTestOnReturn(testOnReturn);
	}

	public boolean isTestWhileIdle() {
		return poolConfig.isTestWhileIdle();
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		poolConfig.setTestWhileIdle(testWhileIdle);
	}

	public long getTimeBetweenEvictionRunsMillis() {
		return poolConfig.getTimeBetweenEvictionRunsMillis();
	}

	public void setTimeBetweenEvictionRunsMillis(
			long timeBetweenEvictionRunsMillis) {
		poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
	}

	public int getNumTestsPerEvictionRun() {
		return poolConfig.getNumTestsPerEvictionRun();
	}

	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
	}

	public long getMinEvictableIdleTimeMillis() {
		return poolConfig.getMinEvictableIdleTimeMillis();
	}

	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
	}

	public long getSoftMinEvictableIdleTimeMillis() {
		return poolConfig.getSoftMinEvictableIdleTimeMillis();
	}

	public void setSoftMinEvictableIdleTimeMillis(
			long softMinEvictableIdleTimeMillis) {
		poolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
	}
}
