package controller;

import java.util.ArrayList;
import java.util.Arrays;

import model.DataWriter;
import model.MapDataBase;

/**
 * @author SA
 * Controls Write operations on {@link MapDataBase}
 */
public class WriteController{

	DataWriter dataWriter;
	
	
	/**
	 * @param new_dataWriter instance of {@link model.DataWriter}
	 */
	public WriteController(DataWriter new_dataWriter){
		dataWriter = new_dataWriter;
	}
	
	/**Writes new continent to map
	 * @param continent_name should be the Continent Name
	 */
	public void writenewContinent(String continentName){
		dataWriter.createContinent(continentName);
	}

	/**
	 * @param editedadjacentCountries are new Adjacent territories given by user
	 * @param continent should be the Continent Name
	 * @param country should be the Country Name
	 * @param continentValue is the continent Value
	 * @param isdeleteContinent is true if a continent is deleted by user
	 * @param isdeleteCountry is true if a country is deleted by user 
	 */
	public void addData(String editedadjacentCountries,String continent,String country,String continentValue,boolean isdeleteContinent,boolean isdeleteCountry){
		
		editedadjacentCountries = editedadjacentCountries.replace("[", "").replace("]", "");
		ArrayList<String> new_adjacentContries = null;
		if(editedadjacentCountries.length() > 2){
			new_adjacentContries = new ArrayList<>(Arrays.asList(editedadjacentCountries.split(",")));
		}else{
			new_adjacentContries = new ArrayList<String>();
		}		 
        if(isdeleteContinent){
			dataWriter.deleteContinent(continent);
		}else if(isdeleteCountry){
			dataWriter.deleteCountry(continent,country);
		}
		else{
			dataWriter.overWriteData(continent,country,continentValue,new_adjacentContries);
		} 
		
	}
	
	
}
