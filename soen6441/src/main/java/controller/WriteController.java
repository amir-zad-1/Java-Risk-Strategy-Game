/**
 * 
 */
package controller;

import java.util.HashMap;

import model.Territory;

/**
 * @author SA
 *
 */
public class WriteController {

	public void writeContinent(String continent_name){
		model.SingletonData.continents.put(continent_name, new HashMap<String,Territory>());
	}
	
	
	
}
