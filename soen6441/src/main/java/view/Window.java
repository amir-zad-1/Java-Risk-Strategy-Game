/**
 * 
 */
package view;

import java.io.File;
import controller.LoadMap;
import controller.WriteMap;
/**
 * @author SA
 *
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
 
public class Window extends Application {
	
    final FileChooser fileChooser = new FileChooser();
    static MapEditorView mapEditor = null;
    static Scene welcomeScreen = null;
    static Stage window  = null;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(final Stage primaryStage) {
    	window = primaryStage;
    	window.setTitle("Game");
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
                	 LoadMap loadMap = new LoadMap(file);
                     loadMap.load(); 
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
                	System.out.println(file);
                    WriteMap writeMap = new WriteMap(file);
                    writeMap.write(); 
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
        window.setScene(welcomeScreen);         
        window.show();
    }
    
    
    public static void loadEditorView(){
    	mapEditor = new MapEditorView(); 
    	window.setScene(mapEditor.getMapEditorView());
    	mapEditor.getCloseButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
            	window.setScene(welcomeScreen); 
            }
    	});
    } 
    
}
