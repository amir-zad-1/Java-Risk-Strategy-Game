/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class holds the all data related to the Map
 * Whenever a .map file is read it is stored in this class memebers
 */
public final class MapDataBase {
	
	/**
	 * {@link #continents} contains all contents 
	 * <tt>The fist key is Continent name</tt>
	 * <tt>The second key is Country name</tt>
	 */
	public static HashMap<String, HashMap<String,Territory>> continents = new HashMap<String, HashMap<String,Territory>>();
	
	/**
	 * {@link #continentValues} contains the values associated to contiennts
	 */
	public static HashMap<String, Integer> continentValues = new HashMap<String, Integer>();		


	/**
	 * @return false if any adjacent territory is not declared in any continent
	 */
	public static boolean isValidAdjacency(){
		Set<String> continentNames = MapDataBase.continents.keySet();
		for(HashMap<String,Territory> territories : MapDataBase.continents.values()){
			for(Territory territory:territories.values()){
				if(territory.getAdjacentTerritories().size() == 0){
					return false;
				}
			     for(String s : territory.getAdjacentTerritories()){
			    	 boolean foundTerritory = false;
			    	 for(String continent: continentNames){
			    	    
			    		 if(MapDataBase.continents.get(continent).containsKey(s)){
			    	    	if(!MapDataBase.continents.get(continent).get(s).getAdjacentTerritories().contains(territory.getName())){
			    	    		return false;
			    	    	}
			    	    	foundTerritory = true;
			    	    }
			    	 }
			    	 if(!foundTerritory) return false;
			     }			    	 
			}
			
		}
		
		return true;
	}

	
	
	/**
	 * @return false is there is any dis-connectivity between to territories or continents 
	 */
	public static boolean isAnyDiconnectivity(){		
        
		HashSet<String> allAdjacencies = new HashSet<>();
		HashMap<String,String> waitingForConnection = new HashMap<>();

		for(String continent : continents.keySet()){
			Set<String> countries = continents.get(continent).keySet();
			for(String territory: countries){
				System.out.println(territory+" "+continent );
				ArrayList<String> tmp = new ArrayList<>();
				tmp.clear();
				tmp.addAll(continents.get(continent).get(territory).getAdjacentTerritories());
				
				if(!allAdjacencies.contains(territory)){
					waitingForConnection.put(territory,continent);
				}else{
					waitingForConnection.remove(territory);
				}
				
				for(String s: tmp){
					if(waitingForConnection.containsKey(s)){
						String continentTmp = waitingForConnection.get(s);
						if(continents.get(continentTmp).get(s).getAdjacentTerritories().size() == 1){
							if(!continents.get(continentTmp).get(s).getAdjacentTerritories().get(0).equals(territory)){
								waitingForConnection.remove(s);	
							}else{

							}
						}else{
							waitingForConnection.remove(s);	
						}
					}
					
				}
				allAdjacencies.addAll(tmp); 
				System.out.println(allAdjacencies);
			    System.out.println(waitingForConnection);
			}
			
		}	
		
		if(waitingForConnection.size()  == 1 && allAdjacencies.size() == 2){
			return true;
		}
		
		if(waitingForConnection.size()  == 0){
			return true;
		}
		
		return false;		
	}

	/**
	 * resets the DataBase
	 */
	public static void clear() {
		continents.clear();
		continentValues.clear();
	}


}
