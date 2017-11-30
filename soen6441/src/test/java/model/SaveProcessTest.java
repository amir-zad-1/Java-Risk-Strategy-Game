/**
 * 
 */
package model;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import model.contract.IPlayer;
import util.expetion.InvalidNumOfPlayersException;

/**
 * This test is to check whether is game is able to save or not
 * @author SA
 */
public class SaveProcessTest {

	Map m;
	GameManager gm;
	SaveProcess saveProcess;

	
	@Before
	public void setUpBeforeClass()
	{		
		saveProcess = new SaveProcess();
	}
	
	
	/**
	 * to check if game if bale to be saved
	 * @throws InvalidNumOfPlayersException if number of players not as per game rules
	 * @throws IOException if unable to read and write game state to a file
	 */
	@Test
	public void saveGame() throws InvalidNumOfPlayersException, IOException{
		m = new Map();
        m.clearData();
        m.fakeData();

        gm = new GameManager(m, 3,"r,r,r", 500);
        gm.start(false);

        IPlayer p1 = gm.nextPlayer();
        p1.reinforcement();
        
        //save the gameMnager
        saveProcess.saveState(gm);
        // get back new game manger
        GameManager gameManager = saveProcess.getPreviousState();
        assertTrue(gameManager.getPlayers().size() == 3);
	}
	
}
