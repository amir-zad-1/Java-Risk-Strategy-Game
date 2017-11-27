/**
 * 
 */
package mapio;


import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;
import model.DataReader;
import model.DataWriter;
import model.LoadMap;
import model.MapDataBase;
import model.Territory;
import model.contract.ITerritory;

/**
 * This tests are to check whether map is loading into MapDatBase
 * @author RTCC
 */
public class MapIOTest {

	static WriteController writeController;
	static ReadController readController;
	
	/**
	 * Load map file from it location path
	 * and use controllers to load it into memory
	 */
	@Before
	public void setUpBeforeClass()
	{
		 //Load map file
		 RWMapFileController rw = new RWMapFileController();
	     rw.loadMap(new File("C:\\Users\\SA\\Downloads\\Earth Alternate.map"));
	     
	     //DataReader only do read operation on map 
	     DataReader dataReader = new DataReader();
	     readController = new ReadController(dataReader);
	     
	     //DataWriter only do write operations on map
	     DataWriter dataWriter = new DataWriter();
	     writeController = new WriteController(dataWriter); 
	}
	
	
	
	
	
	/**
	 * To Check whether able to add a continent to map in memory
	 */
	@Test
	public void addToMapTest(){
		writeController.addData("[America,Newzland]", "Kontinent", "Kontry", "4", false, false);
		assertTrue(readController.getContinentValue("Kontinent") == 4);
	    assertTrue(readController.getAdjacentTerritories("Kontinent", "Kontry").get(0).equals("America"));
	}
	
	/**
	 * This test whether we are able to delete previously added data 
	 */
	@Test
	public void deleteOnMapTest(){
		writeController.addData("[America,Newzland]", "Kontinent", "Kontry", "4", false, false);
		writeController.addData("America,Newzland", "Kontinent", "Kontry", "4", true, false);
		assertFalse(readController.dataReader.hasContinent("Kontinent"));
	}
	
	
	/**
	 * Check if a country has adjacent countries or not
	 */
	@Test
	public void adjacencyTest(){
		ITerritory t= MapDataBase.continents.get("africa").get("east africa");
		ArrayList<ITerritory> tmp= t.getAdjacentTerritoryObjects();
		assertTrue(tmp.size() > 0);
	}
	
	/**
	 * Deprecated as not using controllers for read and write operations
	 */
	@Test
	@Ignore
	public void test() {
		LoadMap loadMap = new LoadMap(new File("C:\\Users\\SA\\Downloads\\Earth Alternate.map"));
        loadMap.load();
		HashMap<String, Territory> terrotories= MapDataBase.continents.get("Tower Left Top");
		for(Territory t: terrotories.values()){
			System.out.println("\n Name "+t.getTerritoryName());
			ArrayList<String> adjacents= t.getAdjacentTerritories();
			for(String s:adjacents){
				System.out.print(s);
			}
		}
	}

}
