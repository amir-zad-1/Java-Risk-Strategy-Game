/**
 * 
 */
package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import controller.GameController;
import controller.RWMapFileController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


/**
 * All the UI related to Welcome View,
 * This is starting UI, which calls other Views
 * @author Team15
 */

public class WelcomeView implements IView{

	GameController gameController = null;
	RWMapFileController maprwController = null;
	static Scene welcomeScreen = null;
	Button stratSavedGame = null, tournamentButton = null;
	

	final FileChooser fileChooser = new FileChooser();
	static MapEditorView mapEditorView = null;
	Stage window = null;
	
	/**
	 * Constructor which injects Controllers and View
	 * @param new_window is the main window on the UI check {@link javafx.stage.Stage}}
	 * @param new_maprwController is the map file read and write controller, check {@link RWMapFileController} 
	 * @param new_mapEditorView is the map editor view, welcome view is responsible to start Map Editor View
	*/
	public WelcomeView(GameController new_gameController,Stage new_window,RWMapFileController new_maprwController, MapEditorView new_mapEditorView) {
		window  = new_window;
		gameController = new_gameController;
		maprwController = new_maprwController; 
		mapEditorView = new_mapEditorView;
	}
	
	
	/**
	 * @return Welcome view Scene, a container which has UI elements and event listeners 
	 * @see Scene
	 */
	public Scene getView(){
		    Button chooseMapButton = new Button();
	        chooseMapButton.setMinWidth(200);
	        chooseMapButton.setText("Choose Map file");
	        Button saveMapButton = new Button();
	        saveMapButton.setMinWidth(200);
	        saveMapButton.setText("Save Map file");
	        Button createMapButton = new Button();
	        createMapButton.setMinWidth(200);
	        createMapButton.setText("Create Map file");
	        Button gobackButton = new Button();
	        gobackButton.setMinWidth(200);
	        gobackButton.setText("Prevoius View");
	        gobackButton.setVisible(false);
	        stratSavedGame = new Button();
	        stratSavedGame.setMinWidth(200);
	        stratSavedGame.setText("Start Saved Game");
	        
	        tournamentButton = new Button();
	        tournamentButton.setMinWidth(200);
	        tournamentButton.setText("Start Tournament");
	        
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setHeaderText("Map file is not valid");

	        
	        
	        
	        chooseMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	                File file = fileChooser.showOpenDialog(window);
	                if(file != null){
	                  boolean isValid = maprwController.loadMap(file); 
	                  if(isValid)
	                	  loadAnotherView(mapEditorView.getView());
	                  else
	                	 alert.showAndWait();	                  
	                }
	                
	            }
	        });
	        
	        saveMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	if(maprwController.validateMap()){
	            		fileChooser.setInitialFileName("NewMap.map");
		            	fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Map File", "map"));
		                File file = fileChooser.showSaveDialog(window);
		                if(file != null){
		                	maprwController.writeMap(file);
		                }	
	            	}else{
	            		 alert.showAndWait();
	            	}	            	
	            }
	        });
	        
	        mapEditorView.getCloseButton().setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	loadAnotherView(welcomeScreen);
	            	gobackButton.setVisible(true);
	            }
	    	});
	        
	        createMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	maprwController.clearData();
	            	loadAnotherView(mapEditorView.getView());
	            }
	        });
	        
	        stratSavedGame.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	gameController.resumeGame();
	            }
	        });
	        
	        gobackButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	loadAnotherView(mapEditorView.getView());
	            }
	        });
	        
	       
	        
	        GridPane gridPane = new GridPane();
	        gridPane.add(chooseMapButton,0,0);
	        gridPane.add(saveMapButton,0,1);
	        gridPane.add(stratSavedGame,0,2);
	        gridPane.add(createMapButton,0,3);
	        gridPane.add(gobackButton,0,4);
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        welcomeScreen = new Scene(gridPane, 300, 250);
		    return welcomeScreen;
	}
	
	
	 /**
	 * Loads new Scene(UI Container) into the window
	 * @param scene will be showed in window
	 * @see java.fx.Scene is has UI elements to load into  Window
	 */
	public  void loadAnotherView(Scene scene){
	    	window.setScene(scene);	
	  } 
	
	/**
	 * @return the gobackButton
	 */
	public Button getResumeButton() {
		return stratSavedGame;
	}


	/**
	 * @return the tournamentButton
	 */
	public Button getTournamentButton() {
		return tournamentButton;
	}

}