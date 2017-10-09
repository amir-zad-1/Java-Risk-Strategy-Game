/**
 * 
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author SA
 *
 */
public class WriteMap {

	File outputPath;
	
	public WriteMap(File file){
		outputPath  = file;
	}
	
	public boolean write(){		
		try {
			FileWriter fw = new FileWriter(outputPath);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("[Continents]");
			bw.newLine();
			for(String key : SingletonData.continentValues.keySet()){
				bw.write(key+"="+SingletonData.continentValues.get(key));
				bw.newLine();
			}
			bw.write("[Territories]");
			bw.newLine();
			for(String key : SingletonData.continents.keySet()){
                ArrayList<Territory> adjacentTerritories = SingletonData.continents.get(key);
                for(Territory t: adjacentTerritories){
                	String tmpStorage = "";
                	for(String s: t.getAdjacentTerritories()){
			    		tmpStorage +=","+s; 
			    	}
			    	bw.write(t.getTerritoryName()+","+t.getCoordinates()+","+t.getContinentName()+","+tmpStorage);
			    	bw.newLine();
			    }
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
