/**
 * 
 */
package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.Player;
import model.Territory;

/**
 * @author SA
 *
 */
public class PhaseView implements IView,Observer{

	Label label;
	
	/** 
	 * @return {@link Scene} which contains UI elements 
	 */
	@Override
	public Scene getView() {		
		label = new Label("Player one");
		label.setStyle("-fx-font-family: 'Saira Semi Condensed', sans-serif;");
		Scene scene = new Scene(label);
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Saira+Semi+Condensed");
		return scene;
	}

	/**
	 * Get called whenever model pushes a change
	 * @param model the model that pushed the update 
	 * @param object optional Object that is passed to notifyObservers
	 */
	@Override
	public void update(Observable model, Object object) {
		label.setText(((Territory)object).getContinentName());
	}
	
}
