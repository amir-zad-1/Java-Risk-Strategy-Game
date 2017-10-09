/**
 * 
 */
package view;

import java.io.File;

import controller.RWMapController;
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
 * @author SA
 *
 */
public class WelcomeView {

	RWMapController maprwController;
	static Scene welcomeScreen = null;
	final FileChooser fileChooser = new FileChooser();
	static MapEditorView mapEditorView = null;
	Stage window = null;
	/**
	 * 
	 */
	public WelcomeView(Stage n_window,RWMapController n_maprwController, MapEditorView n_mapEditorView) {
		window  = n_window;
		maprwController = n_maprwController; 
		mapEditorView = n_mapEditorView;
	}
	
	
	public Scene getWelcomeView(){
		
		 Button chooseMapButton = new Button();
	        chooseMapButton.setMinWidth(200);
	        chooseMapButton.setText("Choose Map file");
	        Button writeMapButton = new Button();
	        writeMapButton.setMinWidth(200);
	        writeMapButton.setText("Save Map file");
	        chooseMapButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	                File file = fileChooser.showOpenDialog(window);
	                if(file != null){
	                	maprwController.loadMap(file); 
	                }
	                loadEditorView();
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
	        
	        GridPane gridPane = new GridPane();
	        gridPane.add(chooseMapButton,0,0);
	        gridPane.add(writeMapButton,0,1);
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        welcomeScreen = new Scene(gridPane, 300, 250);
		    return welcomeScreen;
	}
	
	
	 public  void loadEditorView(){
	    	window.setScene(mapEditorView.getMapEditorView());
	    	mapEditorView.getCloseButton().setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event){
	            	window.setScene(welcomeScreen); 
	            }
	    	});
	    } 
	
}
