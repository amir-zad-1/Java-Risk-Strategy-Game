/**
 * 
 */
package controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.DataReader;
import model.DataWriter;
import model.MapDataBase;
import model.Territory;

/**
 * @author SA
 *
 */
public class ReadControllerTest {

	
	static WriteController writeController;
	static ReadController readController;
	DataReader dataReader;
	/**
	 * Load map file from it location path
	 * and use controllers to load it into memory
	 */
	@Before
	public void setUpBeforeClass()
	{
		 MapDataBase.continentValues.put("asia", 3);
		 MapDataBase.continentValues.put("africa", 2);
		 MapDataBase.continentValues.put("america", 2);
		 ArrayList<String> adjacents = new ArrayList<>();
		 adjacents.add("usa");
		 Territory territory = new Territory("america", "canada", "x,y", adjacents);
		 HashMap<String,Territory> t = new HashMap<>();
		 t.put("canada", territory);
		 MapDataBase.continents.put("america",t);
		 DataWriter dataWriter = new DataWriter();
	     writeController = new WriteController(dataWriter);
	     
	     dataReader = new DataReader();
	     readController = new ReadController(dataReader);
	}
	
	@Test
	public void getContinentValueTest(){
		writeController.addData("[canada,newzland]", "Kontinent", "Kontry", "4", false, false);
		assertTrue(readController.getContinentValue("Kontinent") == 4);
	    assertTrue(readController.getAdjacentTerritories("Kontinent", "Kontry").contains("canada"));
	}
	
	@Test
	public void getAdjacentTerritoriesTest(){
		assertTrue(readController.getAdjacentTerritories("Kontinent", "Kontry").contains("canada"));
	}
	
	
}
