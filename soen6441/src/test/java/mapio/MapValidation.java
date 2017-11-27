/**
 * 
 */
package mapio;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;
import model.DataReader;
import model.DataWriter;
import model.MapDataBase;

/**
 * @author SA
 *
 */
public class MapValidation {

	RWMapFileController rw;
	
	/**
	 * Load map file from it location path
	 * and use controllers to load it into memory
	 */
	@Before
	public void setUpBeforeClass()
	{
		 //Load map file
		 rw = new RWMapFileController();
		 rw.loadMap(new File("C:\\Users\\SA\\Downloads\\Earth Alternate.map"));	  
	}

	
	
	/**
	 * Check if all adjacencies are valid countries 
	 */
	@Test
	public void checkIfAdjacenciesValid(){
	     assertTrue(MapDataBase.isValidAdjacency());
	}
	
	
	/**
	 * Check if there is any dis-connectivity between countries
	 */
	@Test
	public void checkIfAnyDisconnectivity(){
		 assertTrue(MapDataBase.isAnyDiconnectivity());
	}
	
	
	/**
	 * Check if able to reset the Map
	 */
	@Test
	public void mapClearTest(){
		 MapDataBase.clear();
	     assertFalse(MapDataBase.continentValues.containsKey("Africa"));
	}
	
	/**
	 * Check if able able to detect a invalid Map
	 */
	@Test
	public void checkIfValidMap(){		 
		boolean isValid = rw.loadMap(new File("C:\\Users\\SA\\Downloads\\Earth Alternate.map"));
		assertTrue(isValid);
	}
	
	
	/**
	 * Check if able able to detect a invalid Map
	 */
	@Test
	public void checkIfValidMap2(){		 
		boolean isValid = rw.loadMap(new File("C:\\Users\\SA\\Downloads\\World\\sample.map"));
		assertFalse(isValid);
	}
}
