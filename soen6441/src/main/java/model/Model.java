/**
 * 
 */
package model;


/**
 * @author SA
 *
 */
public class Model{

    private static Notifier notifier = null;
    

	public void setNotifier(Notifier new_notifier){
		if(notifier != null){
			notifier  = new_notifier;
		}	
	}
	
	public void sendNotification(String type,Object object){
           notifier.notifyListners(type,object);
	}
	
}
