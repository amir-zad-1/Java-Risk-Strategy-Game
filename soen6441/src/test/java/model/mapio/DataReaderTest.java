/**
 * 
 */
package model.mapio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.mapio.DataReaderTest;
import model.DataReader;
import model.MapDataBase;
import model.Territory;

/**
 * @author SA
 *
 */
public class DataReaderTest {

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
		 
	     //DataReader only do read operation on map 
	     dataReader = new DataReader();
	}
	
	
	@Test
	public void getContinentTest(){
		assertTrue(dataReader.getContries("america").size() == 1);
	}
	
	
	@Test
	public void getAdjacentTerritoriesTest(){
		System.out.println(dataReader.getAdjacentTerritories("america", "canada"));
		assertTrue(dataReader.getAdjacentTerritories("america", "canada").contains("usa"));
	}
	
	@Test
	public void hasContienntTest(){		
		assertTrue(dataReader.hasContinent("asia"));
		assertFalse(dataReader.hasContinent("canada"));
	}
	
	@Test
	public void getContinentValueTest(){		
		assertTrue(dataReader.getContinentValue("asia") == 3);
	}
	
	
}
