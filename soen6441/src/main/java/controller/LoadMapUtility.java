/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.SingletonData;
import model.Territory;

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
	   SingletonData.continents.put(tmpArray[0], new ArrayList<Territory>());
	   SingletonData.continentValues.put(tmpArray[0], Integer.parseInt(tmpArray[1]));
	   return true;
	}
	
	public static boolean addTerritory(String line){
		
	
		String[] tmpArray = line.split(",");		
		if(!SingletonData.continents.containsKey(tmpArray[3])){
			return false;
		}
		ArrayList<String> adjacentTerritories = new ArrayList<String>();
		for(int i=4;i<tmpArray.length;i++){
			adjacentTerritories.add(tmpArray[i].trim());
		}
		SingletonData.continents.get(tmpArray[3]).add(
				new Territory(tmpArray[3].trim(), 
						tmpArray[0],
						tmpArray[1]+","+tmpArray[2],
						adjacentTerritories)
				);
		return true;
	}	
}