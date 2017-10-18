package controller;

import java.util.ArrayList;

import model.DataReader;

/**
 * @author Team15
 *
 */
public class ReadController{

	public DataReader dataReader;
	
	
	public ReadController(DataReader new_dataReader){
	    dataReader = new_dataReader;	
	}
	
	/**
	 * @param continent
	 * @param country
	 * @return ArrayList of adjacent countries names
	 */
	public ArrayList<String> getAdjacentTerritories(String continent,String country){		
		if(dataReader.hasContinent(continent)){
			return dataReader.getAdjacentTerritories(continent,country);			
		}else{			
			return new ArrayList<String>();
		}
	}
	

	/**
	 * @param continent
	 * @return ArrayList of territories names in a continent
	 */
	public ArrayList<String> getTerritoriesNames(String continent) {
		if(!dataReader.hasContinent(continent)){
			return new ArrayList<String>();
		}else{
			return dataReader.getTerritoriesNames(continent);
		}
	}



	/**
	 * Returns continent value
	 * @param continent
	 * @return
	 */
	public int getContinentValue(String continentName) {
		if(dataReader.hasContinent(continentName))
			return dataReader.getContinentValue(continentName);
		else
			return 0;
	}
	
	
}
