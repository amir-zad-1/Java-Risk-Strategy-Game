package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadMap {
    
	private File mapFileLocation = null;
    private Scanner txtScanner = null;
    //private boolean isContinentsLoaded = false;
    
    
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String currentLine = null;
		String beforeContext = null;
		
		while(txtScanner.hasNextLine()){
			currentLine = txtScanner.nextLine().trim();
			if(currentLine.equalsIgnoreCase("[map]")){
				beforeContext = "map";	
			}else if(currentLine.equalsIgnoreCase("[continents]")){
				beforeContext = "continents";
				//isContinentsLoaded = true;
			} else if(currentLine.equalsIgnoreCase("[territories]")){
				beforeContext = "territories";
			}else if(currentLine.length() != 0){
				if(!loadMapToModel(currentLine, beforeContext))
					break;
			}else{
			   	
			}
		}
		
		return true;
	}
	
}