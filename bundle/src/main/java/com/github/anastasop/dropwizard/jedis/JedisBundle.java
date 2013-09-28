package com.github.anastasop.dropwizard.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.yammer.dropwizard.ConfiguredBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.lifecycle.Managed;

/**
 * The dropwizard bundle that adds support for jedis.
 * It adds a jersey Provider for the JedisPool. To use it just
 * declare it in your resource with {@code @Context JedisPool pool}
 * The API of the pool is very similar to the plain JDBC api, in
 * terms of resource management. Code should be very careful to
 * give connections back to the pool to avoid leakage
 */
public abstract class JedisBundle<T extends Configuration> implements ConfiguredBundle<T>, ConfigurationStrategy<T> {
	private JedisPoolConfig jedisPoolConfig;
	private JedisPool jedisPool;

	@Override
	public void run(T configuration, Environment environment) throws Exception {
		JedisConfiguration conf = getJedisConfiguration(configuration);
		jedisPoolConfig = conf.poolConfig;
		jedisPool = new JedisPool(jedisPoolConfig, conf.getHost(), conf.getPort());
		
		environment.addHealthCheck(new JedisPoolHealthCheck("jedis-pool", jedisPool));
		environment.manage(new Managed() {
			@Override
			public void start() throws Exception {
			}

			@Override
			public void stop() throws Exception {
				jedisPool.destroy();
			}
		});
		environment.addProvider(new JedisPoolProvider(jedisPool));
	}

	@Override
	public void initialize(Bootstrap<?> bootstrap) {
	}
	
	public JedisPool getPool() {
		return jedisPool;
	}
}
