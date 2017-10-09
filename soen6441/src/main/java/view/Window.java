/**
 * 
 */
package view;


import controller.RWMapController;
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
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(final Stage window) {    
    	window.setTitle("Game");
    	mapEditorView = new MapEditorView();
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
