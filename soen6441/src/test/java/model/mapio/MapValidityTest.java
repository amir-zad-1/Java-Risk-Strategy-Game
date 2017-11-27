/**
 * 
 */
package model.mapio;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import junit.framework.AssertionFailedError;
import model.MapDataBase;
import model.MapValidity;
import model.Territory;

/**
 * @author SA
 *
 */
public class MapValidityTest {

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
		 
		 //  Adding the territory canada
		 ArrayList<String> adjacents = new ArrayList<>();
		 adjacents.add("usa");
		 Territory territory = new Territory("america", "canada", "x,y", adjacents);
		 HashMap<String,Territory> america = new HashMap<>();
		 america.put("canada", territory);
		 
		 //adding territory usa
		 adjacents = new ArrayList<>();
		 adjacents.add("south africa");
		 territory = new Territory("america", "usa", "x,y", adjacents);
		 america.put("usa", territory);
		 
		 MapDataBase.continents.put("america",america);
		 
		 //adding territory south africa
		 HashMap<String,Territory> africa = new HashMap<>();
		 adjacents = new ArrayList<>();
		 adjacents.add("usa");
		 territory = new Territory("africa", "south africa", "x,y", adjacents);
		 africa.put("south africa", territory);
		 MapDataBase.continents.put("africa",africa);
	 }
	
	
	 @Test
	 public void isValidAdjacencyTest1(){
		 assertTrue(MapValidity.isValidAdjacency());		 
	 }
	
	 @Test
	 public void isAnyDiconnectivityTest1(){
		 assertFalse(MapValidity.isAnyDiconnectivity());		 
	 }
	  
}
