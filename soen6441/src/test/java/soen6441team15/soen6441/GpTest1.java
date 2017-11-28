package soen6441team15.soen6441;
import model.GameManager;
import model.Map;
import model.contract.IMap;
import org.junit.Test;
import util.expetion.InvalidNumOfPlayersException;

/**
 * Tests number of players with minPlayers - 1
 */
public class GpTest1 {

    /**
     * creates a game with 1 player
     * @throws InvalidNumOfPlayersException be careful
     */
    @Test(expected = InvalidNumOfPlayersException.class)
    public void testMinPlayers() throws InvalidNumOfPlayersException
    {
        GameManager gm = new GameManager(1,"r", 500);
        gm.start();
    }


    /**
     * creates a game with 3 player which is correct
     * @throws InvalidNumOfPlayersException be careful
     */
    @Test()
    public void testNormalPlayers() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3,"r,r,r", 500);
    }

}
