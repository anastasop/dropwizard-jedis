package com.github.anastasop.dropwizard.jedis.example.resources;

import java.util.List;

import com.yammer.dropwizard.views.View;

public class TeamView extends View {
	private String name;
	private List<String> members;
	
	public TeamView(String name, List<String> members) {
		super("Team.mustache");
		this.name = name;
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}
}
