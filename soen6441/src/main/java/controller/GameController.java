
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

import model.GameManager;
import model.MapDataBase;
import model.SaveProcess;
import model.Territory;
import model.strategy.Human;
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
	 */
	public GameController(GameManager new_gameManager,SaveProcess new_savePreocess){
		saveProcess = new_savePreocess;
		gameManager = new_gameManager;
	}
	
	/**
	 * Initializes the Game Manager with number of players
	 * @param numberOfPlayers tells numbers of players going to play the game
	 * Have to catch the <code>InvalidNumOfPlayersException</code> exception
	 */
	public void startGame(int numberOfPlayers,String strategies) {		
			isResumedGame = false;
		    try {
				gameManager.startGame(numberOfPlayers,strategies);
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
	 * This method redirects save game request to model
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
	 * @param gameManager the gameManager to set
	 */
	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	/**
	 * 
	 */
	public HashMap<String,String> getUIState() {		
		return saveProcess.getUIState();
		
	}

}
