/**
 * 
 */
package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import model.Player;
import model.Territory;

/**
 * @author SA
 *
 */
public class PhaseView implements IView,Observer{

	HashMap<String,TextArea> playersViews= new HashMap();
	int numberOfPlayers;
	
	/** 
	 * @return {@link Scene} which contains UI elements 
	 */
	@Override
	public Scene getView() {		
		HBox hbox = new HBox();
		hbox.setStyle("-fx-font-family: 'Saira Semi Condensed', sans-serif;");
	    for(int i=1;i<=numberOfPlayers;i++){
	    	TextArea tmp = new TextArea();
	    	tmp.setStyle( 
					"-fx-border-style: solid inside;" + 
							"-fx-border-width: 0 1 0 0;" +  
					"-fx-border-color: black;");
			tmp.setStyle("-fx-padding:10");
	    	hbox.getChildren().add(tmp);
	    	playersViews.put("Player "+i, tmp);
	    }
	    
		Scene scene = new Scene(hbox);
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
		
	   if(object == null){
		   
	   }
		else if(object instanceof Player){
			Player tmp = (Player) object;
			System.err.println();
			playersViews.get(tmp.getName()).setText(tmp.getState());
		}
	}
	
	
	/**
	 * Sets number Of players
	 * @param new_numberofPlayers
	 */
	public void setNumberOfPlayers(int new_numberofPlayers){
		this.numberOfPlayers = new_numberofPlayers;
	}
	
}
