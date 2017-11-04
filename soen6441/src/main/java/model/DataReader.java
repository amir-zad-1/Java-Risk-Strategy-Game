/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author SA
 *
 */
public class DataReader {

	/**
	 * @param continent
	 * @return All territories in a continent
	 */
	public HashMap<String, Territory> getContries(String continent) {
		return MapDataBase.continents.get(continent);
	}

	/**
	 * @param continent
	 * @param country
	 * @return
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
		 * @param continentName
		 * @return true if continent exists
		 */
		public boolean hasContinent(String continentName){
			return MapDataBase.continentValues.containsKey(continentName);		
		}

		/**
		 * @param continent
		 * @return
		 */
		public ArrayList<String> getTerritoriesNames(String continent) {
			return new ArrayList<String>(MapDataBase.continents.get(continent).keySet());
		}

		/**
		 * @param continentName
		 * @return
		 */
		public int getContinentValue(String continentName) {
			return MapDataBase.continentValues.get(continentName);
		}

		/**
		 * @return
		 */
		public java.util.Set<String> getContinents() {			
			return MapDataBase.continentValues.keySet();
		}


	
	
}
