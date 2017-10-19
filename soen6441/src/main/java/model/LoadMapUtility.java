/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author SA
 *
 */
public final class LoadMapUtility {

	
	/**
	 * 
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
	 * Adds territory value to {@link MapDataBase}
	 * @param line
	 * @return false if a territory belongs to an unknown continent
	 */
	public static boolean addTerritory(String line){			
		String[] tmpArray = line.split(",");		
		if(!MapDataBase.continentValues.containsKey(tmpArray[3].toLowerCase())){
			return false;
		}
		ArrayList<String> adjacentTerritories = new ArrayList<String>();
		for(int i=4;i<tmpArray.length;i++){
			adjacentTerritories.add(tmpArray[i].toLowerCase().trim());
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