
package controller;

import java.io.IOException;
import java.util.HashMap;

import model.GameManager;
import model.SaveProcess;
import util.expetion.InvalidNumOfPlayersException;

/**
 * @author Team15
 * Controls the whole Game Play through GameManager
 * @see GameManager 
 */
public class GameController {	

	/**
	 * GameManger takes care about phases of game 
	 */
	GameManager gameManager = null;
	
	/**
	 * {@link #saveProcess} to save the state of game
	 */
	SaveProcess saveProcess = null;
	
	
	boolean isResumedGame = false;
	
	/**
	 * Constructor that initializes the GameManager
	 * @param new_gameManager is the reference of GameManager created in Driver class 
	 * @param new_savePreocess to save the game through game controller
	 */
	public GameController(GameManager new_gameManager,SaveProcess new_savePreocess){
		saveProcess = new_savePreocess;
		gameManager = new_gameManager;
	}
	
	/**
	 * Initializes the Game Manager with number of players
	 * @param numberOfPlayers tells numbers of players going to play the game
	 * @param strategies is the comma separated string example b,r,c 
	 * @param isGameAutomated to check if the game should be automated
	 * Have to catch the <code>InvalidNumOfPlayersException</code> exception
	 */
	public void startGame(int numberOfPlayers,String strategies,boolean isGameAutomated) {		
			isResumedGame = false;
		    try {
		    	if(isGameAutomated)
		    		gameManager.startGame(numberOfPlayers,strategies);
		    	else
		    		new GameManager(numberOfPlayers,strategies,800000).start(true);
			} catch (InvalidNumOfPlayersException e) {
				e.printStackTrace();
			}	
	}


	/**
	 * This method tells the GameManager to start the next round
	 */
	public void askNextTurn() {
		gameManager.takeNextTurn();
	}

	/**
	 * @param text the input given by the HumanPlayer
	 */
	public void submitAnswer(String text) {		
		gameManager.setAnswerForHuman(text);		
	}

	/**
	 * This method redirects save game request to {@link #saveProcess} model
	 * @param uiState is state of UI in {@link HashMap} to save
	 */
	public void saveGame(HashMap<String,String> uiState) {
		try {
			saveProcess.saveState(gameManager);
			saveProcess.saveUIState(uiState);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the GameManager State from previously saved state
	 */
	public GameManager resumeGame() {	
	   isResumedGame = true;
       return saveProcess.getPreviousState();
	}

	/**
	 * @param gameManager the GameManager that will be set only in case of resume game
	 */
	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	/**
	 * @return the previously saved UI state of the game such that it can be used in 
	 * resume game process
	 */
	public HashMap<String,String> getUIState() {		
		return saveProcess.getUIState();
		
	}

}
