/**
 * 
 */
package model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author SA
 *
 */
public class Model extends Observable{

	
	public Model(){
		
	}
	
	public  void notifyModel(Territory t){
		//System.out.println(t.getName() +t.getArmies());
		setChanged();
		notifyObservers();
	}
	
}
