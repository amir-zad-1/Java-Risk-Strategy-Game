/**
 * 
 */
package controller;

import java.io.File;

import model.LoadMap;
import model.WriteMap;

/**
 * @author SA
 *
 */
public class RWMapController {

	/**
	 * 
	 */
	public RWMapController() {
		// TODO Auto-generated constructor stub
	}

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
		// TODO Auto-generated method stub
        WriteMap writeMap = new WriteMap(file);
        writeMap.write(); 

	}
	

}
