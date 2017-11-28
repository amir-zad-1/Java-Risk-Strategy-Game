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
 * @author SA
 *
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
	
	@Test
	public void saveGame() throws InvalidNumOfPlayersException, IOException{
		m = new Map();
        m.clearData();
        m.fakeData();

        gm = new GameManager(m, 3,"r,r,r", 500);
        gm.start(false);

        IPlayer p1 = gm.nextPlayer();
        p1.reinforcement();        
        saveProcess.saveState(gm);
        
        GameManager gameManager = saveProcess.getPreviousState();
        assertTrue(gameManager.getPlayers().size() == 3);
	}
	
}
