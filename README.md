
dropwizard-jedis is a [dropwizard](http://dropwizard.codahale.com/) bundle that adds support for
[jedis](https://github.com/xetorthio/jedis) a popular [redis](http://redis.io) client.

# Usage
First build the project with `mvn install` and add the following dependency to your `pom.xml`

    <dependency>
      <groupId>com.github.anastasop.dropwizard-jedis</groupId>
      <artifactId>bundle</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>

Usage follows dropwizard's conventions. Declare the bundle in the configuration of your service

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

and the inject the jedis pool in your resources with `@Context JedisPool pool`.
The configuration of the pool in the service yaml file, follows the conventions of jedis.

# Example
The module `example` contains a simple application that demonstrates usage. Start redis,
run `run-example.sh` to start the service, then `init-data.sh` to add initial data and finally
open the browser to `http://localhost:8080`.

Enjoy

