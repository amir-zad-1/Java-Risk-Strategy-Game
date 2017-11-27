/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class holds the all data related to the Map
 * Whenever a .map file is read it is stored in this class memebers
 */
public final class MapDataBase {
	
	/**
	 * {@link #continents} contains all contents 
	 * <tt>The fist key is Continent name</tt>
	 * <tt>The second key is Country name</tt>
	 */
	public static HashMap<String, HashMap<String,Territory>> continents = new HashMap<String, HashMap<String,Territory>>();
	
	/**
	 * {@link #continentValues} contains the values associated to continents
	 */
	public static HashMap<String, Integer> continentValues = new HashMap<String, Integer>();		




	/**
	 * resets the DataBase
	 */
	public static void clear() {
		continents.clear();
		continentValues.clear();
	}


}
