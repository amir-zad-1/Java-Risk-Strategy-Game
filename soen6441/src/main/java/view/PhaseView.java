/**
 * 
 */
package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Player;

/**
 * @author SA
 *
 */
public class PhaseView implements IView,Observer{

	HashMap<String,TextArea> playersViews= new HashMap();
	HashMap<String,PlayerStatisticsView> playersStatistics= new HashMap();
	Label phase;
	int numberOfPlayers;
	
	/** 
	 * @return {@link Scene} which contains UI elements 
	 */
	@Override
	public Scene getView() {		
		BorderPane borderPane = new BorderPane();
		phase = new Label();
		phase.setTextFill(Color.GREEN);
		phase.setPadding(new Insets(5,5,5,5));
		HBox hbox = new HBox();
		Label label = new Label("Phase:");
		label.setPadding(new Insets(5,5,5,5));
		label.setTextFill(Color.RED);
		hbox.getChildren().add(label);
		hbox.getChildren().add(phase);
		ToolBar header = new ToolBar(hbox);
		header.setStyle( 
				"-fx-border-style: solid inside;" + 
						"-fx-border-width: 0 0 1 0;" +  
				"-fx-border-color: black;");
		borderPane.setTop(header);

		HBox playerHbox = new HBox();
		hbox.setStyle("-fx-font-family: 'Saira Semi Condensed', sans-serif;");
	    for(int i=1;i<=numberOfPlayers;i++){
	    	VBox vbox= new VBox();
	    	TextArea tmp = new TextArea();
	    	vbox.getChildren().add(tmp);
	    	PlayerStatisticsView pview = new PlayerStatisticsView();
	    	pview.setActorName("Player "+i);
	    	vbox.setStyle( 
					"-fx-border-style: solid inside;" + 
							"-fx-border-width: 0 1 0 0;" +  
					"-fx-border-color: black;");
			tmp.setStyle("-fx-padding:10");
	    	
			vbox.getChildren().add(pview.getPlayerBox());
			
			
			playerHbox.getChildren().add(vbox);
	    
	    	playersStatistics.put("Player "+i, pview);
	    	playersViews.put("Player "+i, tmp);
	    }
	    borderPane.setCenter(playerHbox);
		Scene scene = new Scene(borderPane);
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
		
	   if(object instanceof String){
		   String type = (String)object;
		   switch(type.split(":")[0])
		   {
		   		case "PhaseChange": 
		   			phase.setText(type.split(":")[1]);
		   }
	   }
		else if(object instanceof Player){
			Player tmp = (Player) object;
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
