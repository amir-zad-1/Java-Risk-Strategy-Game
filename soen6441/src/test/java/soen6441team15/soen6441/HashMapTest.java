/**
 * 
 */
package soen6441team15.soen6441;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import controller.LoadMap;
import model.SingletonData;
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
		ArrayList<Territory> terrotories= SingletonData.continents.get("Tower Left Top");
		for(Territory t: terrotories){
			System.out.println("\n Name "+t.getTerritoryName());
			ArrayList<String> adjacents= t.getAdjacentTerritories();
			for(String s:adjacents){
				System.out.print(s);
			}
		}
	}

}
