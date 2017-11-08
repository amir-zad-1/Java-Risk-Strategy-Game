
package controller;

import model.GameManager;
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
	 * Constructor that initializes the GameManager
	 * @param new_gameManager is the reference of GameManager created in Driver class 
	 */
	public GameController(GameManager new_gameManager) {
		gameManager = new_gameManager;
	}
	
	/**
	 * Initializes the Game Manager with number of players
	 * @param numberOfPlayers tells numbers of players going to play the game
	 * Have to catch the <code>InvalidNumOfPlayersException</code> exception
	 */
	public void startGame(int numberOfPlayers) {
		
			try {
				gameManager.startGame(numberOfPlayers);
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



}
