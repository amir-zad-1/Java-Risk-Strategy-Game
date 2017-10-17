/**
 * 
 */
package soen6441team15.soen6441;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import model.LoadMap;
import model.MapDataBase;
import model.Territory;

/**
 * @author RTCC
 *
 */
public class HashMapTest {

	@Test
	public void test() {
		LoadMap loadMap = new LoadMap(new File("E:\\Compressed\\_61_ CASTLE MOONBAT\\_61_ CASTLE MOONBAT.map"));
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
