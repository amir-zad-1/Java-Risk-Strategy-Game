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

	public String notificationType = "null";
	
	public void notifyListners(String type,Object object){
		notificationType = type;
		setChanged();
		notifyObservers(object);		
	}
	
}
