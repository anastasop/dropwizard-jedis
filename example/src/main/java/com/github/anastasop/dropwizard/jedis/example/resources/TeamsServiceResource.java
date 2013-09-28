package com.github.anastasop.dropwizard.jedis.example.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.google.common.base.Splitter;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TeamsServiceResource {
	private static final String KEY_PREFIX = "teams:";
	private static final String KEY_SEARCH = "teams:*";
	private static final Splitter CSV_SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	private JedisPool pool;
	
	private String keyFromTeamName(String teamName) {
		return KEY_PREFIX + teamName;
	}
	
	private String nameFromTeamKey(String key) {
		return key.substring(KEY_PREFIX.length());
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
	public Object getAllTeams(@HeaderParam("Accept") String mediaType) {
		List<String> teams = new ArrayList<String>();
		Jedis jedis = pool.getResource();
		try {
			List<String> teamsKeys = new ArrayList<String>(jedis.keys(KEY_SEARCH));
			for (String key: teamsKeys) {
				teams.add(nameFromTeamKey(key));
			}
			pool.returnResource(jedis);
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			throw e;
		}
		if (mediaType.equalsIgnoreCase(MediaType.APPLICATION_JSON)) {
			return teams;
		}
		return new AllTeamsView(teams);
	}
	
	@GET
	@Path("{name}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
	public Object getMembers(@HeaderParam("Accept") String mediaType, @PathParam("name") String teamName) {
		List<String> members = null;
		Jedis jedis = pool.getResource();
		try {
			if (jedis.exists(keyFromTeamName(teamName))) {
				members = jedis.lrange(keyFromTeamName(teamName), 0, -1);
				pool.returnResource(jedis);
			} else {
				return Response.status(Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			throw e;
		}
		if (mediaType.equalsIgnoreCase(MediaType.APPLICATION_JSON)) {
			return members;
		}
		return new TeamView(teamName, members);
	}
	
	@POST
	@Path("{name}")
	@Consumes("text/plain")
	public void addMembers(@PathParam("name") String teamName, String csvMembers) {
		Jedis jedis = pool.getResource();
		try {
			for (String member: CSV_SPLITTER.split(csvMembers)) {
				jedis.rpush(keyFromTeamName(teamName), member);
			}
			pool.returnResource(jedis);
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			throw e;
		}
	}
}
