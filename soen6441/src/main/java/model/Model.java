/**
 * 
 */
package model;


/**
 * @author SA
 *
 */
public class Model{

    public static Notifier notifier = null;
    

	public void setNotifier(Notifier new_notifier){
			notifier  = new_notifier;
	}
	
	public void sendNotification(String type,Object object){
           notifier.notifyListners(type,object);
	}
	
}
