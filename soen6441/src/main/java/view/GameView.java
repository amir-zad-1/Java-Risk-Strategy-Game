/**
 * 
 */
package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;

/**
 * @author SA
 *
 */
public class GameView implements IView,Observer{

	
	public GameView(){
		
	}
	
	
	/* (non-Javadoc)
	 * @see view.IView#getView()
	 */
	@Override
	public Scene getView() {
		// TODO Auto-generated method stub
		return null;
	}


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
