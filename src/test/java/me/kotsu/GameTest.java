package me.kotsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class GameTest {
	@Test
	void constructorSetsInitialScoresToZeros() {
		Game g = new Game(new Team("A"), new Team("B"));
		assertEquals(0, g.getHomeTeamScore());
		assertEquals(0, g.getAwayTeamScore());
	}
	
	@Test
	void constructorThrowsWhenTeamIsNull() {
		assertThrows(NullPointerException.class, () -> new Game(null, new Team("A")));
		assertThrows(NullPointerException.class, () -> new Game(new Team("A"), null));
		assertThrows(NullPointerException.class, () -> new Game(null, null));
	}
	
	@Test
	void getTeamScoresSummedSumsCorrectly() {
		Game g = new Game(new Team("A"), new Team("B"));
		g.setHomeTeamScore(3);
		g.setAwayTeamScore(9);
		assertEquals(12, g.getTeamsScoresSummed());
	}
	
	@Test
	void toStringFormatsCorrectly() {
		Game g = new Game(new Team("A"), new Team("B"));
		g.setHomeTeamScore(3);
		g.setAwayTeamScore(9);
		assertEquals("A 3 - B 9", g.toString());
	}
}
