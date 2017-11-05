/**
 * 
 */
package model;


/**
 * @author SA
 *
 */
public abstract class Model{

    private static Notifier notifier = null;
    

	public void setNotifier(Notifier new_notifier){
		if(notifier != null){
			notifier  = new_notifier;
		}	
	}
	
	public  void notifyModel(Object t){
           notifier.notifyListners();
	}
	
}
