package controller;

import java.util.ArrayList;
import java.util.Set;

import model.DataReader;
import model.MapDataBase;

/**
 * @author Team15
 * Does read operations on {@link MapDataBase}
 * For every read operation view should call this controller methods
 */
public class ReadController{
	

	/**
	 * Model wrapper that do's different read operations on Map
	 */
	public DataReader dataReader;
	
	
	/**
	 * Constructor to initialize {{@link #dataReader} 
	 * @param new_dataReader instance of {@link model.DataReader}
	 */
	public ReadController(DataReader new_dataReader){
	    dataReader = new_dataReader;	
	}
	
	/**
	 * Return adjacent territories of specified country
	 * @param continent should be the Continent Name
	 * @param country should be the Country Name
	 * @return {@link ArrayList} of adjacent countries names
	 */
	public ArrayList<String> getAdjacentTerritories(String continent,String country){		
		if(dataReader.hasContinent(continent)){
			return dataReader.getAdjacentTerritories(continent,country);			
		}else{			
			return new ArrayList<String>();
		}
	}
	

	/**
	 * Returns names of all territories in a continent
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
	 * Returns the continent value associated with passed continent name
	 * @param continentName should be the Continent Name
	 * @return the continent value if continent exist else 0
	 */
	public int getContinentValue(String continentName) {
		if(dataReader.hasContinent(continentName))
			return dataReader.getContinentValue(continentName);
		else
			return 0;
	}

	/**
	 * @return the set of continent names in the map 
	 */
	public Set<String> getAllContinentNames(){
		return dataReader.getContinents();
	}

	
}
