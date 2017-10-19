package soen6441team15.soen6441;
import model.GameManager;
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
        GameManager gm = new GameManager(null, 1);
        gm.start();
    }

}
