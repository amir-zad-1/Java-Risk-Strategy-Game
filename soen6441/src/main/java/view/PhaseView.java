/**
 * 
 */
package view;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

	
	HashMap<String,PlayerStatisticsView> playersStatistics= new HashMap<String,PlayerStatisticsView>();
	DominationView dominationView = null;
	GameController gameController = null;
	Label phase; String previousPlayer = "";
	Button nextTurn= null;
	int numberOfPlayers;
	
	
	/**
	 * Constructor that initializes the {@link DominationView} 
	 */
	public PhaseView(DominationView new_dominationView,GameController new_gameController) {
		this.dominationView = new_dominationView;
		this.gameController = new_gameController;
	}
	
	/** 
	 * @return {@link Scene} which contains UI elements 
	 */
	@Override
	public Scene getView() {		
		BorderPane borderPane = new BorderPane();
		phase = new Label();
		phase.setTextFill(Color.GREEN);
		phase.setPadding(new Insets(5,5,5,5));
		
		BorderPane hbox = new BorderPane();
		Label label = new Label("Phase:");
		label.setPadding(new Insets(5,5,5,5));
		label.setTextFill(Color.RED);
		hbox.setPadding(new Insets(10,5,0,0));
		hbox.setLeft(new HBox(label, phase));
		hbox.setRight(dominationView.getView());
		
		nextTurn = new Button("Next turn");
		
		hbox.setCenter(nextTurn);
		borderPane.setTop(hbox);
		
		nextTurn.setOnAction(new EventHandler<ActionEvent>() {            
        	@Override
            public void handle(ActionEvent event){
        		playersStatistics.get(previousPlayer).clearStatus();
        		gameController.askNextTurn();	
            }
    	});   
		
		HBox playerHbox = new HBox();
		hbox.setStyle("-fx-font-family: 'Saira Semi Condensed', sans-serif;");
	    for(int i=1;i<=numberOfPlayers;i++){
	    	VBox vbox= new VBox();
	    	//TextArea tmp = new TextArea();
	    	//tmp.setMinHeight(500);
	    	//vbox.getChildren().add(tmp);
	    	PlayerStatisticsView pview = new PlayerStatisticsView();
	    	pview.setActorName("Player "+i);
	    	vbox.setStyle( 
					"-fx-border-style: solid inside;" + 
							"-fx-border-width: 0 1 0 0;" +  
					"-fx-border-color: black;");
			//tmp.setStyle("-fx-padding:10");
	    	
			vbox.getChildren().add(pview.getPlayerBox());
			
			
			playerHbox.getChildren().add(vbox);
	    
	    	playersStatistics.put("Player "+i, pview);
	    	//playersViews.put("Player "+i, tmp);
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
		
           	String type = (String)object;    
           	if(type.equals("PhaseChange")){
           		Player tmp = (Player) object;
           		previousPlayer = tmp.getName();
    			playersStatistics.get(tmp.getName()).setCountriesWon(tmp.getState());
    			
           	}else if(type.split(":")[0].equals("GameChange") || type.split(":")[0].equals("PhaseChange")){
           		phase.setText(type.split(":")[1]);
           	}else if(model instanceof Player){
           		Player tmp = (Player) model;
           		previousPlayer = tmp.getName();
           		playersStatistics.get(tmp.getName()).setCurrentStatus(object.toString());
           		
           		playersStatistics.get(tmp.getName()).setCountriesWon(tmp.getState());
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
