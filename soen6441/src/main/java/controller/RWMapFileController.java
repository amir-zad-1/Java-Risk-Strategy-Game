package controller;

import java.io.File;

import model.MapDataBase;
import model.MapValidity;
import model.mapio.LoadMap;
import model.mapio.WriteMap;

/**
 * @author SA
 * <p>
 * To Load and Save a .map file into {@link MapDataBase}
 * A view should call this class methods to load, save and clear a .map file
 * </p> 
 */
public class RWMapFileController{

	/**
	 * @param file which has path to .map file
	 * @return true is .map file is valid else false
	 * <p>
	 * If a map is disconnected it return false for more see
	 * <a href="http://www.windowsgames.co.uk/conquest_create.html">here</a>
	 * </p>
	 */
	public boolean loadMap(File file) {
		LoadMap loadMap = new LoadMap(file);
        boolean isValid = loadMap.load();
		return isValid;		
	}

	

	/**
	 * This method writes the edited map to the location passed
	 * @param file instance of {@link File} points to save destination
	 */
	public void writeMap(File file) {
        WriteMap writeMap = new WriteMap(file);
        writeMap.write(); 
	}


	/**
	 * Clears the previously loaded data from Map
	 */
	public void clearData() {
		MapDataBase.clear();		
	}

	
	
	/**
	 * Checks the case whether adjacent territories are valid 
	 * @return true if map satisfies above case
	 */
	public boolean validateMap() {
		return MapValidity.isValidAdjacency();		
	}


}
