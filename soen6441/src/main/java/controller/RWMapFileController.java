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
	 * @param file
	 */
	public void loadMap(File file) {
		LoadMap loadMap = new LoadMap(file);
        loadMap.load();		
	}

	

	/**
	 * @param file
	 */
	public void writeMap(File file) {
        WriteMap writeMap = new WriteMap(file);
        writeMap.write(); 
	}	

}
