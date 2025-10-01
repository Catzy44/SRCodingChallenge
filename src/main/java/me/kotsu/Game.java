package me.kotsu;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Game {
	@NonNull private final Team homeTeam;
	@NonNull private final Team awayTeam;
	
	private int homeTeamScore;
	private int awayTeamScore;
	
	public int getTeamsScoresSummed() {
		return homeTeamScore + awayTeamScore;
	}
	
	public void setHomeTeamScore(int score) {
		if(score < 0) {
			throw new IllegalArgumentException("score < 0");
		}
		this.homeTeamScore = score;
	}
	
	public void setAwayTeamScore(int score) {
		if(score < 0) {
			throw new IllegalArgumentException("score < 0");
		}
		this.awayTeamScore = score;
	}
	
	@Override
	public String toString() {
		return String.format("%s %d - %s %d", homeTeam.getTeamName(), homeTeamScore, awayTeam.getTeamName(), awayTeamScore);
	}
}
