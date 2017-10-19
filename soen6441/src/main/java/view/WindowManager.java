
package view;


import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;
/**
 * @author SA
 * 
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.GameManager;
import model.Map;
import model.contract.IMap;
import util.LogMessageEnum;
import util.expetion.InvalidNumOfPlayersException;
 
public class WindowManager extends Application {
	
	
    static WelcomeView welcomeView  = null;
    static MapEditorView mapEditorView = null;
    
    static RWMapFileController rwMapFileController;
	static ReadController readController;
	static WriteController writeController;   
    
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
            	 try
                 {
                     IMap m = new Map();
                     int numberOfPlayers = mapEditorView.getNumberOfPlayers();
                     GameManager gm = new GameManager(m, numberOfPlayers);
                     gm.start();
                 }
                 catch (InvalidNumOfPlayersException e)
                 {
                     Logger.log(LogMessageEnum.ERROT, e.getMessage());
                 }
            }
    	});   
    	
        window.setScene(welcomeView.getView());
        window.show();
    }


	/**
	 * @param new_rwMapFileController
	 * @param new_readController
	 * @param new_writeController
	 */
	public static void addRWControllers(RWMapFileController new_rwMapFileController, ReadController new_readController,
			WriteController new_writeController) {
		
		rwMapFileController = new_rwMapFileController;
		readController = new_readController;
		writeController = new_writeController;		
	}
    
       
}
