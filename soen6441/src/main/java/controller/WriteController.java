package controller;

import java.util.ArrayList;
import java.util.Arrays;

import model.DataWriter;

/**
 * @author SA
 * 
 */
public class WriteController{

	DataWriter dataWriter;
	
	
	public WriteController(DataWriter new_dataWriter){
		dataWriter = new_dataWriter;
	}
	
	/**Writes new continent to map
	 * @param continent_name
	 */
	public void writenewContinent(String continentName){
		dataWriter.createContinent(continentName);
	}

	/**
	 * @param editedadjacentCountries
	 * @param continent
	 * @param country
	 * @param continentValue
	 */
	public void addData(String editedadjacentCountries,String continent,String country,String continentValue,boolean isdeleteContinent,boolean isdeleteCountry){
		
		editedadjacentCountries = editedadjacentCountries.replace("[", "").replace("]", "");
		ArrayList<String> new_adjacentContries = new ArrayList<>(Arrays.asList(editedadjacentCountries.split(",")));
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
