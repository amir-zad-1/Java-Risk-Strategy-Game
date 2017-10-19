package soen6441team15.soen6441;
import model.GameManager;
import model.Map;
import model.contract.IMap;
import model.contract.IPlayer;
import org.junit.Test;
import util.expetion.InvalidNumOfPlayersException;
import org.junit.Assert;

/**
 * Tests number of initial armies
 */
public class GpTest3 {

    @Test
    public void test() throws InvalidNumOfPlayersException
    {
        IMap m = new Map();
        GameManager gm = new GameManager(m, 3);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(35, expected_armies);
    }


}
