package me.kotsu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class Scoreboard {
	private List<Game> gamesInProgress = new ArrayList<Game>();
	public static final String SEPARATOR = "\n";
	private int gameSortIdx = 0;
	
	public Game startNewGame(Team home, Team away) {
		Game newGame = new Game(home, away);
		newGame.setThisGameSortIdx(gameSortIdx++);
		gamesInProgress.add(newGame);
		return newGame;
	}
	public void finishAGame(Game game) {
		if(!gamesInProgress.contains(game)) {
			throw new IllegalArgumentException("Provided Game not belong to this Scoreboard instance");
		}
		gamesInProgress.remove(game);
	}
	public void updateGameScore(Game game, int home, int away  ) {
		if(!gamesInProgress.contains(game)) {
			throw new IllegalArgumentException("Provided Game not belong to this Scoreboard instance");
		}
		game.setHomeTeamScore(home);
		game.setAwayTeamScore(away);
	}
	public String getSummary() throws Exception {
		return gamesInProgress.stream()
				.sorted(
						Comparator
						.comparing(Game::getTeamsScoresSummed, Comparator.reverseOrder())
						.thenComparing(Game::getThisGameSortIdx, Comparator.reverseOrder())
						)
				.map(Game::toString)
				.collect(Collectors.joining(SEPARATOR));
	}
}
