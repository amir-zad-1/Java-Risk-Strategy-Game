package soen6441team15.soen6441;

import model.GameManager;
import model.Map;
import model.contract.IMap;
import org.junit.Assert;
import org.junit.Test;
import util.expetion.InvalidNumOfPlayersException;

/**
 * Players have used all their initial armies before playing
 */
public class GpTest5 {

    @Test()
    public void test() throws InvalidNumOfPlayersException
    {
        IMap m = new Map("");
        GameManager gm = new GameManager(m, 3);
        gm.initGame();
        Assert.assertEquals(0, gm.nextPlayer().getUnusedArmies());
    }

}
