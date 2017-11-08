package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 * This class does the data reading operations on the MapDatabase
 * @author SA 
 */
public class DataReader {

	/**
	 * This method return the territories inside the map for passed continent name
	 * @param continent is the continentName
	 * @return All territories in a continent
	 */
	public HashMap<String, Territory> getContries(String continent) {
		return MapDataBase.continents.get(continent);
	}

	/**
	 * This method returns adjacent territories to a passed country detials
	 * @param continent is the continent name
	 * @param country  is the country name  
	 * @return the {@link ArrayList} of {@link Territory}'s
	 */
	public ArrayList<String> getAdjacentTerritories(String continent, String country) {
		HashMap<String, Territory> territories = getContries(continent);
		for(String teritory_name:territories.keySet()){
			   if(teritory_name.equals(country))
				   return territories.get(country).getAdjacentTerritories();
	     }
		return new ArrayList<String>();
        
	}


		/**
		 * This method checks whether a continent is in exist in the map or not
		 * @param continentName
		 * @return true if a continent exists
		 */
		public boolean hasContinent(String continentName){
			return MapDataBase.continentValues.containsKey(continentName);		
		}

		/**
		 * This method returns all territory names for a passed continent name
		 * @param continent is the continent name
		 * @return {@link ArrayList} of Territory names
		 */
		public ArrayList<String> getTerritoriesNames(String continent) {
			return new ArrayList<String>(MapDataBase.continents.get(continent).keySet());
		}

		/**
		 * @param continentName is just the continent name 
		 * @return the continent value associated with passed contient name
		 */
		public int getContinentValue(String continentName) {
			return MapDataBase.continentValues.get(continentName);
		}

		/**
		 * Returns all the continent names in the loaded map
		 * @return {@link Set} of continent names
		 */
		public java.util.Set<String> getContinents() {			
			return MapDataBase.continentValues.keySet();
		}


	
	
}