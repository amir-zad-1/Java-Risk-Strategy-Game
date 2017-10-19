package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadMap {
    
	private File mapFileLocation = null;
    private Scanner txtScanner = null;
    
    
	/**
	 * Constructor takes map file pointer
	 * @param file instance of {@link File} 
	 */
	public LoadMap(File file){
		mapFileLocation = file;
	}
		
	
	/**
	 * @param data is a line of text from .map file 
	 * @param context tells whether the line is saying about a continent value or territory.
	 * @return
	 */
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
	
	/**
	 * @return false is map is not valid
	 * Note: beforeContext tells whether a reading line belongs to continent values 
	 * or territories declarations. 
	 */
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
		
		return MapDataBase.isValidAdjacency();
	}
	
}