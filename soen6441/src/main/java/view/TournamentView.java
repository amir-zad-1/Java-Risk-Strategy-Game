/**
 * 
 */
package view;

import java.io.File;
import java.util.ArrayList;

import controller.RWMapFileController;
import controller.TournamentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Holds the UI of Tournament ad calls the TournamentController when required
 * @author SA
 */
public class TournamentView implements IView{

	/** Holds the parent window */
	Stage window = null;	
	/** Controller to add .map files */
	RWMapFileController maprwController = null;
	/** Controller to play tournament through */
	TournamentController tournamentController = null;
	/** To choose the path of a .map file */
	final FileChooser fileChooser = new FileChooser();
	/** To hold whole tournament screen */
	static Scene tournamentScreen = null;
	
	
	/**
	 * Constructor to initialize the controllers
	 * @param new_window is the parent window object
	 * @param new_maprwController to initialize the maprwController so .map file
	 * can be written into a data structure
	 * @param new_tournamentController to initialize the tournamentController 
	 */
	public TournamentView(Stage new_window,RWMapFileController new_maprwController,TournamentController new_tournamentController){
		tournamentController = new_tournamentController;
		window = new_window;
		maprwController = new_maprwController;
	}
	
	/** 
	 * @return Scene that has all UI elements
	 * @see view.IView#getView()
	 */
	@Override
	public Scene getView(boolean isResume) {

		//to hold the UI of players in the tournament
		ArrayList<ComboBox<String>> playerList = new ArrayList<>();
		
		//Add map UI
		VBox vboxMapContainer = new VBox();
		Button addMapButton = new Button("Add Map");
		vboxMapContainer.setPadding(new Insets(8));
		vboxMapContainer.getChildren().add(addMapButton);
		
		//Add player UI
		VBox vboxPlayerContainer = new VBox();
		Button addPlayerButton = new Button("Add Player");
		vboxPlayerContainer.setPadding(new Insets(8));
		vboxPlayerContainer.getChildren().add(addPlayerButton);
		
		//Container to hold start button
		VBox vboxStartContainer = new VBox();
		Button startTournamentButton = new Button("Start");
		vboxStartContainer.setPadding(new Insets(8));
		vboxStartContainer.getChildren().add(startTournamentButton);
		
		//to show alert in case of invalid .map
		Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText("Map file is not valid");

		
		//ObservableList for selecting player strategies
		ObservableList<String> options = 
				FXCollections.observableArrayList(
					        "Aggressive",
					        "Benevolent",
					        "Random",
					        "Cheater"
				);
		
		
		addPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ComboBox<String> comboBox = new ComboBox<String>(options);
				playerList.add(comboBox);
				HBox newView = new HBox();
				Label strategyLabel =  new Label("Select Player "+(playerList.size())+" Strategy");
				strategyLabel.setPadding(new Insets(0,5,0,0));
				newView.getChildren().add(strategyLabel);
				newView.getChildren().add(comboBox);
				vboxPlayerContainer.getChildren().add(newView);
				//Set gap between children
				VBox.setMargin(newView, new Insets(8));
			}
		});	
		
		
		addMapButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				  File file = fileChooser.showOpenDialog(window);
	                if(file != null){
	                  boolean isValid = maprwController.loadMap(file); 
	                  if(isValid){
	                	  tournamentController.addAMap();
	                	  vboxMapContainer.getChildren().add(new Label(file.getName()));
	                  } else{
	                	 alert.showAndWait();	                  
	                  }
	              }
			}
		});	
		
		
		startTournamentButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				String tmp = "";
				for(ComboBox<String> selectBox : playerList){
					tmp += ","+(selectBox.getValue()).toLowerCase().charAt(0);
				}
				tournamentController.start(playerList.size(), tmp);
				
			}
		});
		
		HBox wholeContainer = new HBox();
		wholeContainer.setPadding(new Insets(8));
		wholeContainer.getChildren().add(vboxMapContainer);
		wholeContainer.getChildren().add(vboxPlayerContainer);
		wholeContainer.getChildren().add(vboxStartContainer);
		tournamentScreen = new Scene(wholeContainer, 500, 550);
	    return tournamentScreen;
	}
	
	
	

}
