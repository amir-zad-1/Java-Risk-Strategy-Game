/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * Tournament manager that manages the Tournament
 * like adding the maps and starting the 
 * @author SA
 */
public class TournamentManager {
     
	/**
	 * Tournament object which has logic of the tournament
	 */
	Tournament t = null;
	
	
	/**
	 * holds array of maps that are going to played in the tournament
	 */
	ArrayList<Map> mapsInTournament = new ArrayList<>();
	
	
	/**
	 * @param numberOfPlayers is the number of players plating in the tournament
	 * @param playerStrategies is the comma separated string having strategies of players 
	 */
	public void start(int numberOfPlayers, String playerStrategies,int numberOfTurns, int numberOfGames){
		t = new Tournament();
		t.start(mapsInTournament, numberOfPlayers, playerStrategies, numberOfGames, numberOfTurns);
	}
	
	/**
	 * return array list of maps played in the tournament
	 * @return the mapsInTournament
	 */
	public ArrayList<Map> getMapsInTournament() {
		return mapsInTournament;
	}

	/**
	 * this method do
	 * Creating a new Map
	 * and adding it to {@link #mapsInTournament}
	 */
	public void createMap(){
		Map map = new Map();
		addMapsInTournament(map);
	}
	
	
	/**
	 * @param mapInTournament the new that being the part of the Tournament
	 */
	public void addMapsInTournament(Map mapInTournament) {
		this.mapsInTournament.add(mapInTournament);
	}

}
