/**
 * 
 */
package controller;

import java.util.ArrayList;

import model.Territory;

/**
 * @author Team15
 *
 */
public class ReadController {

	
	public ArrayList<String> getAdjacentTerritories(String continent,String country){
		ArrayList<Territory> countries=  model.SingletonData.continents.get(continent);
		for(Territory t:countries){
		   if(t.getTerritoryName().equals(country)){
			   return t.getAdjacentTerritories();
		   }
		}
		return null;
	}
	
	
}
