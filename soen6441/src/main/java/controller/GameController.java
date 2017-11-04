
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
	 * Initializes the Game Manager with number of players
	 * @param numberOfPlayers tells numbers of players going to play the game
	 * Have to catch the <code>InvalidNumOfPlayersException</code> exception
	 */
	public void startGame(int numberOfPlayers) {
		 try
         {
             new GameManager(numberOfPlayers).start();
         }
         catch (InvalidNumOfPlayersException e)
         {
            e.printStackTrace();
         }
		
	}
	
}
