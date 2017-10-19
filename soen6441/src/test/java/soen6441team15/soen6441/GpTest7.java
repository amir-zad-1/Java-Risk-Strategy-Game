package soen6441team15.soen6441;

import model.GameManager;
import model.Map;
import model.contract.IMap;
import model.contract.IPlayer;
import org.junit.Assert;
import org.junit.Test;
import util.expetion.InvalidNumOfPlayersException;
import static org.hamcrest.Matcher.*;


/**
 * Tests minimum reinforcement armies
 */
public class GpTest7 {

    @Test()
    public void test() throws InvalidNumOfPlayersException
    {
        IMap m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3);
        gm.initGame();

        int max_number_of_armies = 10;
        int min_number_of_armies = 3;
        IPlayer p = gm.nextPlayer();
        int reinforcement_armies = gm.calculateReinforcementArmies(p);
        Assert.assertTrue(reinforcement_armies <= max_number_of_armies);
        Assert.assertTrue(reinforcement_armies <= min_number_of_armies);
    }

}
