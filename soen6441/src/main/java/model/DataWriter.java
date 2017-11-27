package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This class do's all the write operations on the map loaded into memory
 * @author SA
 */
public class DataWriter {

	/**
	 * This method creates a continent
	 * @param continentName is the name of the continent to be created
	 */
	public void createContinent(String continentName) {
		MapDataBase.continents.put(continentName, new HashMap<String,Territory>());
		MapDataBase.continentValues.put(continentName, 0);
	}

	/**
	 * Deletes a continent and its countries
	 * @param continent is the continent to be deleted
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
	 * This method is used to delete a country
	 * @param continent is the country continent name
	 * @param country is the name fo the country you want to delete
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
	 * This method writes a new continent to the map which is loaded into memory
	 * @param continent is the name of the continent
	 * @param country is the name of the country
	 * @param continentValue is the value of continent, always get overrides is continent exist
	 * @param new_adjacentContries are the adjacent territories of passed country
	 */
	public void overWriteData(String continent, String country, String continentValue,
			ArrayList<String> new_adjacentContries) {
		
		//If continent not yet exist create it else overrides with new data passed
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