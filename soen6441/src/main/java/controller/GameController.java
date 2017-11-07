
package controller;

import model.GameManager;
import util.expetion.InvalidNumOfPlayersException;

/**
 * @author Team15
 * Controls the whole Game Play through GameManager
 * @see GameManager 
 */
public class GameController {	

	GameManager gameManager = null;
	
	/**
	 * 
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
				//new GameManager(numberOfPlayers).start();
			} catch (InvalidNumOfPlayersException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}


}
