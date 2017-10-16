/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import model.SingletonData;
import model.Territory;

/**
 * @author SA
 *
 */
public class WriteController {

	public void writeContinent(String continent_name){
		model.SingletonData.continents.put(continent_name, new HashMap<String,Territory>());
	}

	/**
	 * @param editedadjacentCountries
	 * @param continent
	 * @param country
	 * @param continentValue
	 */
	public void addData(String editedadjacentCountries,String continent,String country,String continentValue,boolean isdeleteContinent,boolean isdeleteCountry){
		
		editedadjacentCountries = editedadjacentCountries.replace("[", "").replace("]", "");
		ArrayList<String> new_adjacentContries = new ArrayList<>(Arrays.asList(editedadjacentCountries.split(",")));

		if(isdeleteContinent){
			Set<String> contriesContinenthas = SingletonData.continents.get(continent).keySet();
			SingletonData.continentValues.remove(continent);
			for(HashMap<String,Territory> contries  : SingletonData.continents.values()){
			   	for(Territory t:contries.values()){
			   		for(String s:contriesContinenthas){
			   			if(t.getAdjacentTerritories().contains(s)){
				   			t.getAdjacentTerritories().remove(s); 
				   		}
			   		}
			   	}
			}
			SingletonData.continents.remove(continent);
		}else if(isdeleteCountry){
			for(HashMap<String,Territory> contries  : SingletonData.continents.values()){
			   	for(Territory t:contries.values()){
			   			if(t.getAdjacentTerritories().contains(country)){
				   			t.getAdjacentTerritories().remove(country); 
			   		}
			   	}
			}
			SingletonData.continents.get(continent).remove(country);
			
		}
		else if(!SingletonData.continents.containsKey(continent)){
			SingletonData.continents.put(continent, new HashMap<String,Territory>());
			if(!country.isEmpty() && !country.equals("Countries")){
				Territory territory =  new Territory(continent, country, "x,y", new_adjacentContries);
				territory.setAdjacentTerritories(new_adjacentContries);
				SingletonData.continents.get(continent).put(country, territory);
			}
			SingletonData.continentValues.put(continent, Integer.parseInt(continentValue));
		} else if(!SingletonData.continents.get(continent).containsKey(country)){
			Territory territory =  new Territory(continent, country, "x,y", new_adjacentContries);
			territory.setAdjacentTerritories(new_adjacentContries);
			SingletonData.continents.get(continent).put(country, territory);
		}else{
			SingletonData.continents.get(continent).get(country).setAdjacentTerritories(new_adjacentContries);
		}		
		
	}
	
	
	
}
