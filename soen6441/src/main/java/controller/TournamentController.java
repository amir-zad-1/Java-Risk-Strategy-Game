/**
 * 
 */
package controller;

import model.TournamentManager;

/**
 * @author SA
 *
 */
public class TournamentController {

	
	TournamentManager tournamentManager = null; 
	
	public TournamentController(TournamentManager new_tournamentManager){
		tournamentManager = new_tournamentManager;
	}
	
	
	public void addAMap(){
		tournamentManager.createMap();
	}
	
}
