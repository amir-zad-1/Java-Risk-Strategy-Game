/**
 * 
 */
package soen6441team15.soen6441;

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
	     rw.loadMap(new File("C:\\Users\\m_guntur\\Downloads\\Earth Alternate\\Earth Alternate.map"));
	     
	     //DataReader only do read operation on map 
	     DataReader dataReader = new DataReader();
	     readController = new ReadController(dataReader);
	     
	     //DataWriter only do write operations on map
	     DataWriter dataWriter = new DataWriter();
	     writeController = new WriteController(dataWriter); 
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
}
