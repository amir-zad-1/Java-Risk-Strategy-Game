/**
 * 
 */
package model;

import java.util.ArrayList;

/**
 * @author SA
 *
 */
public class TournamentManager {
     
	Tournament t = null;
	ArrayList<Map> mapsInTournament = new ArrayList<>();
	
	
	public void start(int numberOfPlayers, String playerStrategies){
		t = new Tournament();
		t.start(mapsInTournament, numberOfPlayers, playerStrategies, 4, 30);
	}
	
	/**
	 * @return the mapsInTournament
	 */
	public ArrayList<Map> getMapsInTournament() {
		return mapsInTournament;
	}

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
