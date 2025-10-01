package me.kotsu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;

@Getter
public class Scoreboard {
	private List<Game> gamesInProgress = new ArrayList<Game>();
	public static final String SEPARATOR = "\n";
	
	public Game startNewGame(Team home, Team away) {
		Game newGame = new Game(home, away);
		gamesInProgress.add(newGame);
		return newGame;
	}
	public void finishAGame(Game game) {
		gamesInProgress.remove(game);
	}
	public void updateGameScore(Game game, int home, int away  ) {
		game.setHomeTeamScore(home);
		game.setAwayTeamScore(away);
	}
	public String getSummary() throws Exception {
		return gamesInProgress.stream()
				.sorted(Comparator.comparing(Game::getTeamsScoresSummed))
				.map(Game::toString)
				.reduce("", (a,b) -> a+SEPARATOR+b);
	}
}
