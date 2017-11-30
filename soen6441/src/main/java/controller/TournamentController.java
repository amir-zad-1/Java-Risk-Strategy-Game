/**
 * 
 */
package controller;

import model.TournamentManager;

/**
 * <p> This class controls the Tournament 
 * through {@link #tournamentManager} </p>
 * @author SA
 */
public class TournamentController {

	
	/**
	 * {@link #tournamentManager} manages the tournament like adding maps and players
	 */
	TournamentManager tournamentManager = null; 
	
	/**
	 * Constructor to initialize the {@link #tournamentManager}
	 * @param new_tournamentManager is the {@link TournamentManager} such that
	 * this controller manages the tournament through it
	 */
	public TournamentController(TournamentManager new_tournamentManager){
		tournamentManager = new_tournamentManager;
	}
	
	/**
	 * @param numberOfPlayers are the number of players in the tournament
	 * @param playerStrategies is comma separated string of strategies of players
	 * @param turns tells number of turns
	 * @param noOfGames tells number of games to play in tournament
	 */
	public void start(int numberOfPlayers, String playerStrategies,String turns, String noOfGames){
		tournamentManager.start(numberOfPlayers, playerStrategies,
				Integer.parseInt(turns), Integer.parseInt(noOfGames));
	}
	
	/**
	 * Adds a map to the Tournament
	 */
	public void addAMap(){
		tournamentManager.createMap();
	}
	
}
