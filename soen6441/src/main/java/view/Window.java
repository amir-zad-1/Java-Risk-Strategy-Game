/**
 * 
 */
package view;


import controller.RWMapController;
import controller.ReadController;
/**
 * @author SA
 *
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
 
public class Window extends Application {
	
	
    static WelcomeView welcomeView  = null;
    static MapEditorView mapEditorView = null;
    ReadController readController = new ReadController();
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(final Stage window) {    
    	window.setTitle("Game");
    	mapEditorView = new MapEditorView(readController);
    	welcomeView = new WelcomeView(window,new RWMapController(), mapEditorView); 
        
    	mapEditorView.getStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
            	//start Game play here
            }
    	});   
    	
    	window.setScene(welcomeView.getWelcomeView());         
        window.show();
    }
    
    
   
    
}
