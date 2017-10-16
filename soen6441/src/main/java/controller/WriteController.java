/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
	 * @param continents
	 * @param contries
	 * @param editedadjacentCountries
	 * @param continent
	 * @param country
	 * @param continentValue
	 */
	public void saveData(List<String> continents, List<String> contries, String editedadjacentCountries,String continent,String country,String continentValue){
		
		editedadjacentCountries = editedadjacentCountries.replace("[", "").replace("]", "");
		ArrayList<String> new_adjacentContries = new ArrayList<>(Arrays.asList(editedadjacentCountries.split(",")));
		
		if(continents.size() != SingletonData.continents.size()){
			continents.removeAll(SingletonData.continents.keySet());
		}else{
			continents.clear();
		}
		
		if(!SingletonData.continents.containsKey(continent)){
			SingletonData.continents.put(continent, new HashMap<String,Territory>());     	
		}		
		
		if(contries.size() != SingletonData.continents.get(continent).size()){
			contries.removeAll(SingletonData.continents.get(continent).keySet());
		}else{
			contries.clear();
		}
		
		if(new_adjacentContries.size() != SingletonData.continents.get(continent).get(country).getAdjacentTerritories().size()){
			new_adjacentContries.removeAll(SingletonData.continents.get(continent).get(country).getAdjacentTerritories());
		}else{
			new_adjacentContries.clear();
		}
		
		if(continents.size() > 0){
			for(String new_continent: continents){
				HashMap<String,Territory> temp = new HashMap<String,Territory>(); 
				for(String new_contry:contries){
					temp.put(new_contry, new Territory(new_continent, new_contry, "x,y", new_adjacentContries));
				}
				SingletonData.continents.put(new_continent,temp);				
			}
		}else if(contries.size() > 0){
			for(String new_contry:contries){
				SingletonData.continents.get(continent).put(new_contry, new Territory(continent, new_contry, "x,y", new_adjacentContries));
			}
		} else if(new_adjacentContries.size() > 0){
		   SingletonData.continents.get(continent).get(country).getAdjacentTerritories().addAll(new_adjacentContries);
		} else{
           		     	
		}
		
		SingletonData.continentValues.put(continent, Integer.getInteger(continentValue));
		
		System.out.println(Arrays.toString(continents.toArray()));
		System.out.println(Arrays.toString(contries.toArray()));
		System.out.println(Arrays.toString(new_adjacentContries.toArray()));
	}
	
	
	
}
