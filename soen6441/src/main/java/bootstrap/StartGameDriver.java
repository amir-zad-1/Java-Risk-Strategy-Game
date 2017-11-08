
package bootstrap;

import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.WriteController;

import model.DataReader;
import model.DataWriter;
import model.GameManager;

import model.Player;
import view.DominationView;
import view.PhaseView;
import view.WindowManager;

/**
 * @author Manohar Guntur
 * This class starts the game
 * And gives connection between View, Controller and Model Objects.
 * So its a driver class
 */
public class StartGameDriver {

	/** 
	 * <li>Step 0: Initialize the controllers</li>
	 * <li>Step 1: Create the Views</li>
	 * <li>Step 2: Inject controllers into Views through {@link WindowManager} </li>
	 * <li>Add Observers to players whenever users gives number of players input</li>
	 * @param args from CMD
	 */
	public static void main(String[] args) {
		
		 //Creates Controller to controls read and write the .map file
		 RWMapFileController rwMapFileController = new RWMapFileController();
		 
		 //Creates controller, which is responsible to redirect Read operations
		 ReadController readController = new ReadController(new DataReader());
		 
		 //Creates controller, which is responsible to redirect Write operations
		 WriteController writeController = new WriteController(new DataWriter());
		 
		 
		 //Creates Game Manager and sends it to GameController
		 GameManager gameManager = new GameManager();
		 GameController gameController = new GameController(gameManager);
		 
	 
		 
		 //Creates Domination View and make it Observer
		 DominationView dominationView = new DominationView();
		 
		 
		 //Creates phaseView make it Observer

		 PhaseView phaseView = new PhaseView(dominationView,gameController);

		 
		 
		 
		 //Sends all controllers to view manager, such that views can contact
		 WindowManager.addControllers(rwMapFileController,readController,writeController,gameController);
		 WindowManager.setView(phaseView,"phaseview");
		 
		 //Create Players objects and add observers only when users gives number of player inputs
		 WindowManager.addCallBack(new CallBack(){
			    public void called(int numberOfPlayers){
			    	for(int i=1;i<=numberOfPlayers;i++){
			    		Player p = new Player("Player " + Integer.toString(i));
			    		p.addObserver(dominationView);
			    		p.addObserver(phaseView);
			    		gameManager.addPlayer(p);
			    	}
			    	
			    }
		 });
		 
		 
		 gameManager.addObserver(dominationView);
		 gameManager.addObserver(phaseView);
		 
		 javafx.application.Application.launch(WindowManager.class);
	}

}
