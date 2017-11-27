/**
 * 
 */
package model.mapio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.MapDataBase;
import model.MapValidity;
import model.Territory;

/**
 * @author SA
 *
 */
public class MapValidityTest1 {

	/**
	 * Load map file from into HashMap
	 */
	@Before
	public void setUpBeforeClass()
	{
		 MapDataBase.clear(); 
		 MapDataBase.continentValues.put("africa", 2);
		 MapDataBase.continentValues.put("america", 2);
		 
		 //  Adding the territory canada
		 ArrayList<String> adjacents = new ArrayList<>();
		 adjacents.add("usa");
		 Territory territory = new Territory("america", "canada", "x,y", adjacents);
		 HashMap<String,Territory> america = new HashMap<>();
		 america.put("canada", territory);
		 
		 //adding territory usa
		 ArrayList<String> adjacents1 = new ArrayList<>();
		 adjacents1.add("south africa");
		 adjacents1.add("canada");
		 Territory territory1 = new Territory("america", "usa", "x,y", adjacents1);
		 america.put("usa", territory1);
		 
		 MapDataBase.continents.put("america",america);
		 
		 //adding territory south africa
		 HashMap<String,Territory> africa = new HashMap<>();
		 ArrayList<String> adjacents2 = new ArrayList<>();
		 adjacents2.add("usa");
		 Territory territory2 = new Territory("africa", "south africa", "x,y", adjacents2);
		 africa.put("south africa", territory2);
		 MapDataBase.continents.put("africa",africa);
	 }
	
	
	 @Test
	 public void isValidAdjacencyTest1(){
		 assertTrue(MapValidity.isValidAdjacency());		 
	 }
	
	 @Test
	 public void isAnyDiconnectivityTest1(){
		 assertTrue(MapValidity.isAnyDiconnectivity());		 
	 }
	
	
}
