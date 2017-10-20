package controller;

import java.io.File;

import model.LoadMap;
import model.MapDataBase;
import model.WriteMap;

/**
 * @author SA
 * This class controls read and writes on the map
 */
public class RWMapFileController{

	/**
	 * @param file instance of {@link File} points to .map file
	 */
	public boolean loadMap(File file) {
		LoadMap loadMap = new LoadMap(file);
        return loadMap.load();		
	}

	

	/**
	 * @param file instance of {@link File} points to new .map file
	 */
	public void writeMap(File file) {
        WriteMap writeMap = new WriteMap(file);
        writeMap.write(); 
	}



	/**
	 * Checks the case whether adjacent territories are valid 
	 * @return true if map satisfies above case
	 */
	public boolean validateMap() {
		return MapDataBase.isValidAdjacency();		
	}



	/**
	 * Clears the previously loaded data from Map
	 */
	public void clearData() {
		MapDataBase.clear();		
	}	

}
