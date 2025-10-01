package me.kotsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreboardTest {
	@Test
	void constructorConstructsEmptyScoreboard() {
		Scoreboard sb = new Scoreboard();
		assertTrue(sb.getGamesInProgress().isEmpty());
		assertTrue(sb.getSummary().isEmpty());
	}
	
	@Test
	void startNewGameAddsNewGameToScoreboard() {
		Scoreboard sb = new Scoreboard();
		sb.startNewGame(new Team("A"), new Team("B"));
		assertEquals(sb.getGamesInProgress().size(), 1);
	}
	
	@Test
	void finishAGameRemovesGameFromScoreboard() {
		Scoreboard sb = new Scoreboard();
		Game g = new Game(new Team("A"), new Team("B"));
		sb.startNewGame(g);
		sb.finishAGame(g);
		assertTrue(sb.getGamesInProgress().isEmpty());
	}
	
	@Test
	void finishANonExistentGameThrows() {
		Scoreboard sb = new Scoreboard();
		sb.startNewGame(new Team("A"), new Team("B"));
		
		Game fakeGame = new Game(new Team("C"), new Team("D"));
		
		String summaryBeforeTry = sb.getSummary();
		assertThrows(IllegalArgumentException.class, () -> sb.finishGame(fakeGame));
		
		assertEquals(summaryBeforeTry, sb.getSummary());
	}
	
	@Test
	void updateGameScoreUpdatesGameObject() {
		Scoreboard sb = new Scoreboard();
		Game g = new Game(new Team("A"), new Team("B"));
		sb.startNewGame(g);
		sb.updateGameScore(g, 6, 9);
		
		assertTrue(g.getHomeTeamScore() == 6);
		assertTrue(g.getAwayTeamScore() == 9);
	}
	
	@Test
	void updateGameScoreForNonExistentGameTHrows() {
		Scoreboard sb = new Scoreboard();
		sb.startNewGame(new Team("A"), new Team("B"));
		
		Game fakeGame = new Game(new Team("C"), new Team("D"));
		
		String summaryBeforeTry = sb.getSummary();
		assertThrows(IllegalArgumentException.class, () -> sb.updateGameScore(fakeGame, 1, 1));
		assertEquals(summaryBeforeTry, sb.getSummary());
	}
	
	@Test
	void summaryReturnsCorrectDataInPredefiniedFormat() {
		Scoreboard sb = new Scoreboard();
		Game g = new Game(new Team("A"), new Team("B"));
		sb.startNewGame(g);
		sb.updateGameScore(g, 10, 2);
		
		Game g1 = new Game(new Team("C"), new Team("D"));
		sb.startNewGame(g1);
		sb.updateGameScore(g1, 6, 6);//first one

		Game g2 = new Game(new Team("E"), new Team("F"));
		sb.startNewGame(g2);
		sb.updateGameScore(g2, 3, 3);
		
		String summary = sb.getSummary();
		assertNotNull(summary);
		assertFalse(summary.isEmpty());
		assertTrue(summary.contains(Scoreboard.SEPARATOR));
		
		String fragments[] = summary.split(Scoreboard.SEPARATOR);
		assertTrue(fragments.length == 3);
		
		assertEquals(fragments[0], g1.toString());// C6 D6 (12 (added later = on top
		assertEquals(fragments[1], g.toString());//A10 B2 (12
		assertEquals(fragments[2], g2.toString());//E3 F3 (6
	}
}
