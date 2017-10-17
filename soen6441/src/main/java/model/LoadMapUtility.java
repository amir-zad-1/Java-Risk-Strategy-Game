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
public final class LoadMapUtility {

	
	public static boolean addContinet(String line){
	   String[] tmpArray = line.split("=");
	   if(tmpArray.length != 2){
		   return false;
	   }
	   MapDataBase.continents.put(tmpArray[0], new HashMap<String,Territory>());
	   MapDataBase.continentValues.put(tmpArray[0], Integer.parseInt(tmpArray[1]));
	   return true;
	}
	
	public static boolean addTerritory(String line){
		
	
		String[] tmpArray = line.split(",");		
		if(!MapDataBase.continents.containsKey(tmpArray[3])){
			return false;
		}
		ArrayList<String> adjacentTerritories = new ArrayList<String>();
		for(int i=4;i<tmpArray.length;i++){
			adjacentTerritories.add(tmpArray[i].trim());
		}
		MapDataBase.continents.get(tmpArray[3]).put(tmpArray[0],
				new Territory(tmpArray[3].trim(), 
						tmpArray[0],
						tmpArray[1]+","+tmpArray[2],
						adjacentTerritories)
				);
		return true;
	}	
}