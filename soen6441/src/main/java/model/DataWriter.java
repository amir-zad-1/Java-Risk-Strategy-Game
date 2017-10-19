/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @author SA
 *
 */
public class DataWriter {

	/**
	 * @param continentName
	 */
	public void createContinent(String continentName) {
		MapDataBase.continents.put(continentName, new HashMap<String,Territory>());
	}

	/**
	 * Deletes a continent and its countries
	 * @param continent
	 */
	public void deleteContinent(String continent) {
		Set<String> contriesContinenthas = MapDataBase.continents.get(continent).keySet();
		MapDataBase.continentValues.remove(continent);
		for(HashMap<String,Territory> contries  : MapDataBase.continents.values()){
		   	for(Territory t:contries.values()){
		   		for(String s:contriesContinenthas){
		   			if(t.getAdjacentTerritories().contains(s)){
			   			t.getAdjacentTerritories().remove(s); 
			   		}
		   		}
		   	}
		}
		MapDataBase.continents.remove(continent);		
	}

	/**
	 * @param continent
	 * @param country
	 */
	public void deleteCountry(String continent, String country) {
		for(HashMap<String,Territory> contries  : MapDataBase.continents.values()){
		   	for(Territory t:contries.values()){
		   			if(t.getAdjacentTerritories().contains(country)){
			   			t.getAdjacentTerritories().remove(country); 
		   		}
		   	}
		}
		MapDataBase.continents.get(continent).remove(country);		
	}

	/**
	 * @param continent
	 * @param country
	 * @param continentValue
	 * @param new_adjacentContries
	 */
	public void overWriteData(String continent, String country, String continentValue,
			ArrayList<String> new_adjacentContries) {
		if(!MapDataBase.continents.containsKey(continent)){
			MapDataBase.continents.put(continent, new HashMap<String,Territory>());
			if(!country.isEmpty() && !country.equals("Countries")){
				Territory territory =  new Territory(continent, country, "x,y", new_adjacentContries);
				territory.setAdjacentTerritories(new_adjacentContries);
				MapDataBase.continents.get(continent).put(country, territory);
			}
		} else if(!MapDataBase.continents.get(continent).containsKey(country)){
			Territory territory =  new Territory(continent, country, "x,y", new_adjacentContries);
			territory.setAdjacentTerritories(new_adjacentContries);
			MapDataBase.continents.get(continent).put(country, territory);
		}else{
		    MapDataBase.continents.get(continent).get(country).setAdjacentTerritories(new_adjacentContries);
		}
		MapDataBase.continentValues.put(continent,Integer.parseInt(continentValue));
	}

}
