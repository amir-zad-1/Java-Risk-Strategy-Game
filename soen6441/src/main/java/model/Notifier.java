/**
 * 
 */
package model;

import java.util.Observable;

/**
 * @author SA
 *
 */
public class Notifier extends Observable{

	
	public void notifyListners(String type,Object object){
		setChanged();
		notifyObservers();
	}
	
}
