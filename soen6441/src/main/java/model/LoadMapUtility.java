
package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class priveds helper classes to convert .map file into {@link HashMap}
 * @author mdazhar
 */
public final class LoadMapUtility {

	
	/**
	 * This method converts a continent declaration text line into a continent
	 * @param line, is a text line from .map file
	 * @return false if unable to split by '='
	 */
	public static boolean addContinet(String line){
	   String[] tmpArray = line.split("=");
	   if(tmpArray.length != 2){
		   return false;
	   }
	   MapDataBase.continents.put(tmpArray[0].toLowerCase(), new HashMap<String,Territory>());
	   MapDataBase.continentValues.put(tmpArray[0].toLowerCase(), Integer.parseInt(tmpArray[1]));
	   return true;
	}
	
	
	
	/**
	 * Adds territory details to {@link MapDataBase}
	 * @param line is the text line that describes about a territory
	 * @return false if a territory belongs to an unknown continent
	 */
	public static boolean addTerritory(String line){		
		
		//As .map file has territory details comma separated 
		String[] tmpArray = line.split(",");		
		
		//If country belongs to unknown continent return false
		if(!MapDataBase.continentValues.containsKey(tmpArray[3].toLowerCase())){
			return false;
		}
		ArrayList<String> adjacentTerritories = new ArrayList<String>();
		for(int i=4;i<tmpArray.length;i++){
			adjacentTerritories.add(tmpArray[i].toLowerCase().trim());
		}
		
		if(adjacentTerritories.size() == 0){
			return false;
		}
		MapDataBase.continents.get(tmpArray[3].toLowerCase()).put(tmpArray[0].toLowerCase(),
				new Territory(tmpArray[3].trim().toLowerCase(), 
						tmpArray[0].toLowerCase(),
						tmpArray[1]+","+tmpArray[2],
						adjacentTerritories)
				);
		return true;
	}	
}