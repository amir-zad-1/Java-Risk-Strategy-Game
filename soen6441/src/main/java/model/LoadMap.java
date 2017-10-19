package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LoadMap {
    
	private File mapFileLocation = null;
    private Scanner txtScanner = null;
    
    
	public LoadMap(File file){
		mapFileLocation = file;
	}
	
	private boolean loadMapToModel(String data,String context){
		boolean isValidMap = true;
		switch(context){
			case "continents":
				isValidMap = LoadMapUtility.addContinet(data);
			    break;
			case "territories":
				isValidMap = LoadMapUtility.addTerritory(data);
			default:
				break;
		}
		
		if(!isValidMap){
		   	return false;
		}
		return true;
	}
	
	public boolean load(){
		try {
			txtScanner = new Scanner(new FileInputStream(mapFileLocation));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String currentLine = null;
		String beforeContext = "none";
		
		while(txtScanner.hasNextLine()){
			currentLine = txtScanner.nextLine().trim();
			if(currentLine.equalsIgnoreCase("[map]")){
				beforeContext = "map";	
			}else if(currentLine.equalsIgnoreCase("[continents]")){
				beforeContext = "continents";
			} else if(currentLine.equalsIgnoreCase("[territories]")){
				beforeContext = "territories";
			}else if(currentLine.length() != 0){
				if(!loadMapToModel(currentLine, beforeContext))
					return false;					
			}else{
			   	
			}
		}
		isValidAdjacency();
		return true;
	}
	
	
	public boolean isValidAdjacency(){
		for(HashMap<String,Territory> territories : MapDataBase.continents.values()){
			for(Territory territory:territories.values()){
			     for(String s : territory.getAdjacentTerritories()){
			    	    if(!MapDataBase.continents.get(territory.getContinentName()).containsKey(s))
			    	    	return false;
			     }			    	 
			}
			
		}
		
		return true;
	}
	
}