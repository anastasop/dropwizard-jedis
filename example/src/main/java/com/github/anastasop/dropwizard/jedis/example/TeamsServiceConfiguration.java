package com.github.anastasop.dropwizard.jedis.example;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.anastasop.dropwizard.jedis.JedisConfiguration;
import com.yammer.dropwizard.config.Configuration;

public class TeamsServiceConfiguration extends Configuration {
	@NotNull
	@JsonProperty
	private JedisConfiguration jedis;

	public JedisConfiguration getJedis() {
		return jedis;
	}

	public void setJedis(JedisConfiguration jedis) {
		this.jedis = jedis;
	}
}
