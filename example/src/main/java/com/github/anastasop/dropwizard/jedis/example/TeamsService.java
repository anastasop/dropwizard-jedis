package com.github.anastasop.dropwizard.jedis.example;

import com.github.anastasop.dropwizard.jedis.JedisBundle;
import com.github.anastasop.dropwizard.jedis.JedisConfiguration;
import com.github.anastasop.dropwizard.jedis.example.resources.TeamsServiceResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;

public class TeamsService extends Service<TeamsServiceConfiguration> {
	private JedisBundle<TeamsServiceConfiguration> jedisBundle = new JedisBundle<TeamsServiceConfiguration>() {
		@Override
		public JedisConfiguration getJedisConfiguration(
				TeamsServiceConfiguration configuration) {
			return configuration.getJedis();
		}
	};
	
	@Override
	public void initialize(Bootstrap<TeamsServiceConfiguration> bootstrap) {
		bootstrap.setName("dropwizard-jedis-example");
		bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
		bootstrap.addBundle(new ViewBundle());
		bootstrap.addBundle(jedisBundle);
	}

	@Override
	public void run(TeamsServiceConfiguration configuration,
			Environment environment) throws Exception {
		environment.addResource(TeamsServiceResource.class);
	}
	
	public static void main(String[] args) throws Exception {
		new TeamsService().run(args);
	}
}
