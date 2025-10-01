package me.kotsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GameTest {
	
	@Test
	void constructorSetsInitialScoresToZeros() {
		Game g = new Game(new Team("A"), new Team("B"));
		assertEquals(g.getHomeTeamScore(), 0);
		assertEquals(g.getAwayTeamScore(), 0);
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
		assertTrue(g.getTeamsScoresSummed() == 12);
	}
	
	@Test
	void toStringFormatsCorrectly() {
		Game g = new Game(new Team("A"), new Team("B"));
		g.setHomeTeamScore(3);
		g.setAwayTeamScore(9);
		assertEquals(g.toString(), "A 3 - B 9");
	}
}
