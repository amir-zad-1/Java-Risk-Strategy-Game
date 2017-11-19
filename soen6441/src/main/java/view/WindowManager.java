package view;

import bootstrap.CallBack;
import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

 
/**
 * @author Team15
 * Is the glue between the views
 */
public class WindowManager extends Application {
	
	
    static WelcomeView welcomeView  = null;
    static MapEditorView mapEditorView = null;
    public static PhaseView phaseView = null;
    
    
    static RWMapFileController rwMapFileController;
	static ReadController readController;
	static GameController gameController;
	static WriteController writeController;
	static CallBack callback;
    
    /**
     * Starts the main UI window
     * and initializes the Editor and Welcome View
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage window) {
    	
    	window.setTitle("Game");
    	
    	mapEditorView = new MapEditorView(readController,writeController);
		welcomeView = new WelcomeView(window, rwMapFileController, mapEditorView); 
		mapEditorView.getStartGameButton().setOnAction(new EventHandler<ActionEvent>() {            
        	@Override
            public void handle(ActionEvent event){
        		int numberOfPlayers = mapEditorView.getNumberOfPlayers();
        		String strategies = mapEditorView.getPlayersStrategies();
        		phaseView.setNumberOfPlayers(numberOfPlayers);
        		phaseView.setWindowStage(window);
        		window.setScene(phaseView.getView());
        		callback.called(numberOfPlayers);
        		gameController.startGame(numberOfPlayers,strategies);
        		
            }
    	});   
       
		window.setScene(welcomeView.getView());
        window.show();
    }


	/**
	 * This method is used to initialize all the controllers
	 * @param new_rwMapFileController  is the .map IO controller 
	 * @param new_readController is map reader controller
	 * @param new_writeController is the map write controller
	 * @param new_gameController controlls the game start
	 */
	public static void addControllers(RWMapFileController new_rwMapFileController, ReadController new_readController,
			WriteController new_writeController,GameController new_gameController) {		
		rwMapFileController = new_rwMapFileController;
		readController = new_readController;
		writeController = new_writeController;
		gameController = new_gameController; 
	}


	/**
	 * Sets required View
	 * @param view which implements {@link IView}
	 * @param typeOfView defines type of view to be set
	 */
	public static void setView(IView view, String typeOfView) {
		
		switch(typeOfView){
			case "phaseview":
				phaseView = (PhaseView)view;
				break;
			default:
				break;
		}
		
	}


	/**
	 * @param new_callBack to initialize the callback functionality
	 */
	public static void addCallBack(CallBack new_callBack) {		
		callback = new_callBack;
	}
    
	
       
}
