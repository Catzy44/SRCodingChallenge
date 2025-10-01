package me.kotsu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {
	private String teamName;
	
	public Team(String teamName) throws IllegalArgumentException {
		if(teamName == null) {
			throw new IllegalArgumentException("Team name is NULL!");
		}
		if(teamName.isEmpty()) {
			throw new IllegalArgumentException("Team name is EMPTY!");
		}
		this.teamName = teamName;
	}
}