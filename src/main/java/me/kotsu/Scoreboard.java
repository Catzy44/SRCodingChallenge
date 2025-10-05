package me.kotsu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Scoreboard {
	public static final String SEPARATOR = "\n";
	
	private final List<Game> gamesInProgress = new ArrayList<Game>();
	private int gameSortIdx = 0;
	
	public Game startNewGame(Team home, Team away) {
		Game newGame = new Game(home, away); //throws on NULL value - Lombok @NonNull and @RequiredArgsConstructor inside Game class
		newGame.setThisGameSortIdx(gameSortIdx++);
		gamesInProgress.add(newGame);
		return newGame;
	}
	
	public void finishAGame(Game game) {
		if(!gamesInProgress.contains(game)) {
			throw new IllegalArgumentException("Provided Game does not belong to this Scoreboard instance");
		}
		gamesInProgress.remove(game);
	}
	
	public void updateGameScore(Game game, int home, int away  ) {
		if(!gamesInProgress.contains(game)) {
			throw new IllegalArgumentException("Provided Game does not belong to this Scoreboard instance");
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
