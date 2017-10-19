/**
 * 
 */
package model;

import java.util.HashMap;
import java.util.Set;

/**
 * @author SA
 *
 */
public final class MapDataBase {
	public static HashMap<String, HashMap<String,Territory>> continents = new HashMap<String, HashMap<String,Territory>>();
	public static HashMap<String, Integer> continentValues = new HashMap<String, Integer>();		


	/**
	 * @return false if any adjacent territory is not declared in any continent
	 */
	public static boolean isValidAdjacency(){
		Set<String> continentNames = MapDataBase.continents.keySet();
		for(HashMap<String,Territory> territories : MapDataBase.continents.values()){
			for(Territory territory:territories.values()){
			     for(String s : territory.getAdjacentTerritories()){
			    	 boolean foundTerritory = false;
			    	 for(String continent: continentNames){
			    	    if(MapDataBase.continents.get(continent).containsKey(s))
			    	    	foundTerritory = true;
			    	 }
			    	 if(!foundTerritory) return false;
			     }			    	 
			}
			
		}
		
		return true;
	}


	/**
	 * resets the DataBase
	 */
	public static void clear() {
		continents.clear();
		continentValues.clear();
	}


}
