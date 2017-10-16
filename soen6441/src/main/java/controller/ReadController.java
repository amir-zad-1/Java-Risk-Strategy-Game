/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Territory;

/**
 * @author Team15
 *
 */
public class ReadController {

	
	public ArrayList<String> getAdjacentTerritories(String continent,String country){
		HashMap<String,Territory> countries=  model.SingletonData.continents.get(continent);
		
		if(countries == null){
			return new ArrayList<String>();
		}else{
			System.out.println("got countrie");
		for(String teritory_name:countries.keySet()){
		   if(teritory_name.equals(country)){
			   return countries.get(country).getAdjacentTerritories();
		   }
		  }
		}
		return new ArrayList<String>();
	}
	
	
	


	/**
	 * @param continent
	 * @return ArrayList of territories names in a continent
	 */
	public ArrayList<String> getTerritoriesNames(String continent) {
		HashMap<String, Territory> countries=  model.SingletonData.continents.get(continent);
		if(countries == null){
			return new ArrayList<String>();
		}else{
			return new ArrayList<String>(countries.keySet());
		}
	}



	public boolean hasContinent(String continentName){
		return model.SingletonData.continentValues.containsKey(continentName);		
	}


	/**
	 * Returns continent value
	 * @param continent
	 * @return
	 */
	public int getContinentValue(String continentName) {
		if(hasContinent(continentName))
			return model.SingletonData.continentValues.get(continentName);
		else
			return 0;
	}
	
	
}
