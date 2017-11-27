/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author SA
 *
 */
public class MapValidity {

	/**
	 * @return false if any adjacent territory is not declared in any continent
	 */
	public static boolean isValidAdjacency(){
		Set<String> continentNames = MapDataBase.continents.keySet();
		boolean isnotConnetWithSameContinent = true;
		
		for(HashMap<String,Territory> territories : MapDataBase.continents.values()){
			for(Territory territory:territories.values()){
				isnotConnetWithSameContinent = true;
				if(territory.getAdjacentTerritories().size() == 0){
					return false;
				}
			     for(String s : territory.getAdjacentTerritories()){
			    	 boolean foundTerritory = false;
			    	 for(String continent: continentNames){
			    	     if(MapDataBase.continents.get(continent).containsKey(s)){
			    	    	if(continent.equals(territory.getContinentName()) ){
			    	    	    isnotConnetWithSameContinent = false;
			    	    	}
			    	    	foundTerritory = true;
			    	    }
			    	 }
			    	 if(!foundTerritory) return false;
			     }			    	 
			  
			     if(isnotConnetWithSameContinent  && MapDataBase.continents.get(territory.getContinentName()).size() > 1){
			    	  if(!anyOneSaidIhave(territory.getContinentName(),territory.getName())){
			    	     return false;
			    	   }
			     }
			}
			
	    	 
		}
		
		return true;
	}

	
	
	/**
	 * @param continent
	 * @param territory
	 * @return true if anyone in same continent tells passed territory is adjacent
	 */
	private static boolean anyOneSaidIhave(String continent, String territory) {
		
		
		HashMap<String,Territory> territories  =  MapDataBase.continents.get(continent);
		
		for(String territoryName : territories.keySet()){
			if(!territoryName.equals(territory)){
				for(String adjacent : territories.get(territoryName).getAdjacentTerritories()){
					if(adjacent.equals(territory)){
						return true;
					}
				}
		   }
		}
		
		return false;
	}



	/**
	 * @return false is there is any dis-connectivity between to territories or continents 
	 */
	public static boolean isAnyDiconnectivity(){		
        
		HashSet<String> allAdjacencies = new HashSet<>();
		HashMap<String,String> waitingForConnection = new HashMap<>();
		for(String continent : MapDataBase.continents.keySet()){
			Set<String> countries = MapDataBase.continents.get(continent).keySet();
			for(String territory: countries){
				ArrayList<String> tmp = new ArrayList<>();
				tmp.clear();
				tmp.addAll(MapDataBase.continents.get(continent).get(territory).getAdjacentTerritories());
				
				if(!allAdjacencies.contains(territory)){
					waitingForConnection.put(territory,continent);
				}else{
					waitingForConnection.remove(territory);
				}
				for(String s: tmp){
					if(waitingForConnection.containsKey(s)){
						String continentTmp = waitingForConnection.get(s);
						if(MapDataBase.continents.get(continentTmp).get(s).getAdjacentTerritories().size() == 1){
							if(!MapDataBase.continents.get(continentTmp).get(s).getAdjacentTerritories().get(0).equals(territory)){
								waitingForConnection.remove(s);	
							}
							else if(tmp.size()>1){
								waitingForConnection.remove(s);
							}
							else{

							}
						}else{
							waitingForConnection.remove(s);	
						}
					}
					
				}
				allAdjacencies.addAll(tmp); 
		
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
	
	
}
