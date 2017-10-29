/**
 * 
 */
package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.Territory;

/**
 * @author SA
 *
 */
public class PhaseView implements IView,Observer{

	Label label = new Label("Player one");
	
	/* (non-Javadoc)
	 * @see view.IView#getView()
	 */
	@Override
	public Scene getView() {		
        label.setStyle("-fx-font-family: 'Saira Semi Condensed', sans-serif;");
		Scene scene = new Scene(label);
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Saira+Semi+Condensed");
		return scene;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Chnaged");
		Territory T = ((Territory) arg0);
		label.setText(label.getText()+"\n"+T.getName()+" Armies: "+T.getArmies());
        //System.out.println("dd "+((Territory) arg0).getName());
	}

	
	
}
