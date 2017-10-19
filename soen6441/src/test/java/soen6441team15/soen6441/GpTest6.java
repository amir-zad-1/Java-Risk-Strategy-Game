package soen6441team15.soen6441;

import model.GameManager;
import model.Map;
import model.contract.IMap;
import model.contract.IPlayer;
import org.junit.Assert;
import org.junit.Test;
import util.expetion.InvalidNumOfPlayersException;

/**
 * Tests minimum reinforcement armies
 */
public class GpTest6 {

    @Test()
    public void test() throws InvalidNumOfPlayersException
    {
        IMap m = new Map("");
        GameManager gm = new GameManager(m, 3);
        gm.initGame();

        IPlayer p = gm.nextPlayer();
        Assert.assertEquals(3, gm.calculateReinforcementArmies(p));
    }

}
