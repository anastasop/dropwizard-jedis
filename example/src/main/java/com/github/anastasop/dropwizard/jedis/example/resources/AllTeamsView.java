package com.github.anastasop.dropwizard.jedis.example.resources;

import java.util.List;

import com.yammer.dropwizard.views.View;

public class AllTeamsView extends View {
	private List<String> teams;
	
	public AllTeamsView(List<String> teams) {
		super("AllTeams.mustache");
		this.teams = teams;
	}

	public List<String> getTeams() {
		return teams;
	}

	public void setTeams(List<String> teams) {
		this.teams = teams;
	}
}
