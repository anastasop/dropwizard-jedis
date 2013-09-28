package com.github.anastasop.dropwizard.jedis;

import com.yammer.dropwizard.config.Configuration;

public interface ConfigurationStrategy<T extends Configuration> {
    JedisConfiguration getJedisConfiguration(T configuration);
}
