package me.kotsu;

public class SRCodinChallengeApp {
	public static void main(String args[]) {
		try {
			new SRCodinChallengeApp().start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws Exception {
		Scoreboard scoreboard = new Scoreboard();
		
		{
			Game g = scoreboard.startNewGame(new Team("Mexico"), new Team("Canada"));
			scoreboard.updateGameScore(g, 0, 5);
		}
		
		{
			Game g = scoreboard.startNewGame(new Team("Spain"), new Team("Brazil"));
			scoreboard.updateGameScore(g, 10, 2);
		}
		
		{
			Game g = scoreboard.startNewGame(new Team("Germany"), new Team("France"));
			scoreboard.updateGameScore(g, 2, 2);
		}
		
		{
			Game g = scoreboard.startNewGame(new Team("Uruguay"), new Team("Italy"));
			scoreboard.updateGameScore(g, 6, 6);
		}
		
		{
			Game g = scoreboard.startNewGame(new Team("Argentina"), new Team("Australia"));
			scoreboard.updateGameScore(g, 3, 1);
		}
		
		System.out.println(scoreboard.getSummary());
		
		scoreboard.getGamesInProgress().forEach(game -> scoreboard.finishAGame(game));
	}
}
