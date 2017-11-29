package view;

import bootstrap.CallBack;
import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.TournamentController;
import controller.WriteController;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.GameManager;

 
/**
 * @author Team15
 * Is the glue between the views
 */
public class WindowManager extends Application {
	
	
    static WelcomeView welcomeView  = null;
    static MapEditorView mapEditorView = null;
    public static PhaseView phaseView = null;
    
    
    static RWMapFileController rwMapFileController;
    static TournamentController tournamentController;
	static ReadController readController;
	static GameController gameController;
	static WriteController writeController;
	static CallBack[] callback = new CallBack[3];
    
    /**
     * Starts the main UI window
     * and initializes the Editor and Welcome View
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage window) {
    	
    	window.setTitle("Game");
    	
    	mapEditorView = new MapEditorView(readController,writeController);
		welcomeView = new WelcomeView(gameController,window, rwMapFileController, mapEditorView);
		
		
		mapEditorView.getStartGameButton().setOnAction(new EventHandler<ActionEvent>() {            
        	@Override
            public void handle(ActionEvent event){
        		int numberOfPlayers = mapEditorView.getNumberOfPlayers();
        		String strategies = mapEditorView.getPlayersStrategies();
        		phaseView.setNumberOfPlayers(numberOfPlayers);
        		phaseView.setWindowStage(window);
        		window.setScene(phaseView.getView(false));
        		callback[0].called(numberOfPlayers,strategies);
        		gameController.startGame(numberOfPlayers,strategies);
        		
            }
    	});   
       
			
		window.setScene(welcomeView.getView(false));
        
		welcomeView.getResumeButton().setOnAction(new EventHandler<ActionEvent>() {            
        	@Override
            public void handle(ActionEvent event){        		
        		GameManager gm = gameController.resumeGame();
        		callback[2].called(gm);
        		callback[1].called(0,"");
        		phaseView.setNumberOfPlayers(2);
        		phaseView.setWindowStage(window);
        		window.setScene(phaseView.getView(true));        		
        	}
		 });
		
		welcomeView.getTournamentButton().setOnAction( new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			   TournamentView tournamentView = new TournamentView(window, rwMapFileController, tournamentController);	
			   window.setScene(tournamentView.getView(false));		        
			}
			
		});
		
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
			WriteController new_writeController,GameController new_gameController, TournamentController new_tournamentController) {		
		rwMapFileController = new_rwMapFileController;
		readController = new_readController;
		writeController = new_writeController;
		gameController = new_gameController;
		tournamentController = new_tournamentController;
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
	static int i =0;
	public static void addCallBack(CallBack new_callBack) {
		callback[i++] = new_callBack;
	}
    
	
       
}
