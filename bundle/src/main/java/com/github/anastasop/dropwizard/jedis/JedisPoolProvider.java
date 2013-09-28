package com.github.anastasop.dropwizard.jedis;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import redis.clients.jedis.JedisPool;

import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;

@Provider
public class JedisPoolProvider extends SingletonTypeInjectableProvider<Context, JedisPool> {
	public JedisPoolProvider(JedisPool pool) {
		super(JedisPool.class, pool);
	}
}
