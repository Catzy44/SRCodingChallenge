package me.kotsu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TeamTest {
	
	@Test
	void constructorCreatesTeamWithValidName() throws IllegalArgumentException {
		Team t = new Team("A");
		assertEquals("A", t.getTeamName());
	}
	
	@Test
	void constructorThrowsOnNullName() {
		assertThrows(IllegalArgumentException.class, () -> new Team(null));
	}

	@Test
	void constructorThrowsOnEmptyName() {
		assertThrows(IllegalArgumentException.class, () -> new Team(""));
	}
}
