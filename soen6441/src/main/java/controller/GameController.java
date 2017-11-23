
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import model.GameManager;
import model.SaveProcess;
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
	public void saveGame() {
		try {
			saveProcess.saveState(gameManager);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void resumeGame() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("tempdata.tiger");
			ObjectInputStream ois = new ObjectInputStream(fis);
	        gameManager = (GameManager) ois.readObject();
	        ois.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
