/**
 * 
 */
package view;

import java.io.File;

import controller.RWMapFileController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

	RWMapFileController maprwController;
	static Scene welcomeScreen = null;
	final FileChooser fileChooser = new FileChooser();
	static MapEditorView mapEditorView = null;
	Stage window = null;
	
	/**
	 * Constructor which injects Controllers and View
	 * @param new_window is the main window on the UI check {@link javafx.stage.Stage}}
	 * @param new_maprwController is the map file read and write controller, check {@link RWMapFileController} 
	 * @param new_mapEditorView is the map editor view, welcome view is responsible to start Map Editor View
	*/
	public WelcomeView(Stage new_window,RWMapFileController new_maprwController, MapEditorView new_mapEditorView) {
		window  = new_window;
		maprwController = new_maprwController; 
		mapEditorView = new_mapEditorView;
	}
	
	
	/**
	 * @return Welcome view Scene, which has UI elements and event listeners 
	 * @see Scene
	 */
	public Scene getView(){
		    Button chooseMapButton = new Button();
	        chooseMapButton.setMinWidth(200);
	        chooseMapButton.setText("Choose Map file");
	        Button writeMapButton = new Button();
	        writeMapButton.setMinWidth(200);
	        writeMapButton.setText("Save Map file");
	        
	        Button createMapButton = new Button();
	        createMapButton.setMinWidth(200);
	        createMapButton.setText("Create Map file");
	        
	        
	        chooseMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	                File file = fileChooser.showOpenDialog(window);
	                if(file != null){
	                	maprwController.loadMap(file); 
	                }
	                loadAnotherView(mapEditorView.getView());
	            }
	        });
	        
	        writeMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	fileChooser.setInitialFileName("NewMap.map");
	            	fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Map File", "map"));
	                File file = fileChooser.showSaveDialog(window);
	                if(file != null){
	                	maprwController.writeMap(file);
	                }
	            }
	        });
	        
	        
	        createMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	loadAnotherView(mapEditorView.getView());
	            }
	        });
	        
	        GridPane gridPane = new GridPane();
	        gridPane.add(chooseMapButton,0,0);
	        gridPane.add(writeMapButton,0,1);
	        gridPane.add(createMapButton,0,2);
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        welcomeScreen = new Scene(gridPane, 300, 250);
		    return welcomeScreen;
	}
	
	
	 /**
	 * Loads new Scene(UI Container) into window
	 */
	public  void loadAnotherView(Scene scene){
	    	window.setScene(scene);
	    	mapEditorView.getCloseButton().setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	window.setScene(welcomeScreen); 
	            }
	    	});
	  } 
	
}