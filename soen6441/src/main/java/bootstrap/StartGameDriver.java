
package bootstrap;


import java.util.ArrayList;

import controller.GameController;
import controller.RWMapFileController;
import controller.ReadController;
import controller.TournamentController;
import controller.WriteController;

import model.DataReader;
import model.DataWriter;
import model.GameManager;

import model.Player;
import model.SaveProcess;
import model.TournamentManager;
import model.contract.IStrategy;
import model.strategy.Aggressive;
import model.strategy.Benevolent;
import model.strategy.Cheater;
import model.strategy.Human;
import model.strategy.Random;
import view.CardView;
import view.DominationView;
import view.HumanPlayerView;
import view.PhaseView;
import view.WindowManager;

/**
  
 * This class starts the game
 * And gives connection between Views, Controllers and Model Objects.
 * So its a driver class
 * @author Manohar Gunturu
 */
public class StartGameDriver {

	
	/**
	 * {@link #gameManager} to attach it GameController
	 */
	static GameManager gameManager;
	
	/**
	 * tells whether a human is playing or not
	 */
	static boolean isHumanPlaying = false;
	
	
	/**
	 * {@link #humanPlayerView} takes care about the UI interface of Humna player
	 */
	static HumanPlayerView humanPlayerView = null;
	
	/** 
	 * <ul>
	 * <li>Step 0: Initialize the controllers</li>
	 * <li>Step 1: Create the Views</li>
	 * <li>Step 2: Inject controllers into Views through {@link WindowManager} </li>
	 * <li>Add Observers to players whenever users gives number of players input</li>
	 * </ul>
	 * @param args from CMD
	 */
	public static void main(String[] args) {
		
		 //Creates Controller to controls read and write the .map file
		 RWMapFileController rwMapFileController = new RWMapFileController();
		 
		 //Creates controller, which is responsible to redirect Read operations
		 ReadController readController = new ReadController(new DataReader());
		 
		 //Creates controller, which is responsible to redirect Write operations
		 WriteController writeController = new WriteController(new DataWriter());
		 
		 //Creates controller and manger who are responsible to play the tournament
		 TournamentManager tournamentManager = new TournamentManager();
		 TournamentController tournamentController = new TournamentController(tournamentManager);
		 
		 //Creates Game Manager and sends it to GameController
		 gameManager = new GameManager();
		 
		 //To save the state of the game
		 SaveProcess saveProcess = new SaveProcess();
		 GameController gameController = new GameController(gameManager,saveProcess);

		 
		 //Creates Domination View and to make it Observer
		 DominationView dominationView = new DominationView();
		 
		 //Instantiate the human player view
		 humanPlayerView = new HumanPlayerView(gameController);
		 
		 //Creates phaseView make it Observer
		 CardView  cardView= new CardView();
		 PhaseView phaseView = new PhaseView(dominationView,gameController,cardView,humanPlayerView);		 
		 
		 
		 //Sends all controllers to view manager, such that views can contact with view manager whenever they want a controller
		 WindowManager.addControllers(rwMapFileController,readController,writeController,gameController,tournamentController);
		 WindowManager.setView(phaseView,"phaseview");
		 
		 //Create Players objects and add observers only when users gives number of player inputs
		 WindowManager.addCallBack(new CallBack(){
			    public void called(int numberOfPlayers, String strategies){
			    	gameManager.addObserver(dominationView);
					gameManager.addObserver(phaseView);
					setStrategies(strategies);
			    	for(int i=1;i<=numberOfPlayers;i++){
			    		Player p = new Player("Player " + Integer.toString(i));
			    		p.addObserver(dominationView);
			    		p.addObserver(phaseView);
			    		p.addObserver(cardView);
			    		gameManager.addPlayer(p);
			    	}			    	
			    }
		 });
		 
		 
		 //this callback is called whenever user want to resume the previous game
		 WindowManager.addCallBack(new CallBack(){
			    public void called(int numberOfPlayers, String strategies){
			    	ArrayList<Player> playerList = gameManager.getPlayers();			    	
			        for(Player p : playerList){
			        	p.addObserver(dominationView);
			        	p.addObserver(cardView);
			    		p.addObserver(phaseView);	
			        }
			        gameManager.addObserver(dominationView);
					gameManager.addObserver(phaseView);
					gameManager.addObserver(cardView);
					ArrayList<IStrategy> playerStrategies = gameManager.getStrategies();
					for(int i = 0;i<playerStrategies.size();i++){
						if(playerStrategies.get(i).getName().equals("Human")){
							Human h = (Human)playerStrategies.get(i);
							h.addObserver(humanPlayerView);
							playerStrategies.set(i, h);
						}
					}
			    }
		 });
		
		//this callback is called whenever user want to resume the previous game
		 WindowManager.addCallBack(new CallBack(){
			    public <T> void called(T object){
			    	gameManager = (GameManager) object;
			    	gameController.setGameManager(gameManager);
			    }
		 });	    
		 
		 javafx.application.Application.launch(WindowManager.class);
	}
	
	
	
	 /**
     * set strategies according to strategies string
     * @param strategyString contains strategies of players going to play the game 
     * sample for 3 players with Human, Random and Aggressive strategies is h,r,a
     */
	public static void setStrategies(String strategyString)
    {
        String[] stra = strategyString.split(",");
        
        for(String s:stra)
        {
        	switch (s)
            {
                case "h":
                	isHumanPlaying = true;
                	Human humanStratergy = new Human();
                	humanStratergy.addObserver(humanPlayerView);
                	gameManager.addStrategies(humanStratergy);
                	break;
                case "a":
                	IStrategy agressive = new Aggressive();
                	gameManager.addStrategies(agressive);
                	break;
                case "b":
                	gameManager.addStrategies(new Benevolent());
                	break;
                case "r":
                	gameManager.addStrategies(new Random());
                	break;
                case "c":
                	gameManager.addStrategies(new Cheater());
                	break;
            }
        }

    }

}
