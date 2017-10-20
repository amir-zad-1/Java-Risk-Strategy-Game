package controller;

import java.util.ArrayList;

import model.DataReader;
import model.MapDataBase;

/**
 * @author Team15
 * Does read operations on {@link MapDataBase}
 */
public class ReadController{

	public DataReader dataReader;
	
	/**
	 * @param new_dataReader instance of {@link model.DataReader}
	 */
	public ReadController(DataReader new_dataReader){
	    dataReader = new_dataReader;	
	}
	
	/**
	 * @param continent should be the Continent Name
	 * @param country should be the Country Name
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
	 * @param continent should be the Continent Name
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
	 * @param continent should be the Continent Name
	 * @return the continent value
	 */
	public int getContinentValue(String continentName) {
		if(dataReader.hasContinent(continentName))
			return dataReader.getContinentValue(continentName);
		else
			return 0;
	}
	
	
}
