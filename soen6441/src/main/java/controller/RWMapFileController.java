package controller;

import java.io.File;

import model.LoadMap;
import model.WriteMap;

/**
 * @author SA
 *
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

}
