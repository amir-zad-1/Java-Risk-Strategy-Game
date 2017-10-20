/**
 * 
 */
package controller;

import model.GameManager;
import util.expetion.InvalidNumOfPlayersException;

/**
 * @author Team15
 * Controls the whole Game Play 
 */
public class GameController {	

	/**
	 * Initializes the Game
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
