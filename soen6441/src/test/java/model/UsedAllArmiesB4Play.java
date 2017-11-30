package model;

import model.GameManager;
import model.Map;
import model.contract.IMap;
import org.junit.Assert;
import org.junit.Test;
import util.expetion.InvalidNumOfPlayersException;

/**
 * Players have used all their initial armies before playing
 */
public class UsedAllArmiesB4Play {

    @Test()
    public void test() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();
        GameManager gm = new GameManager(m, 3,"r,r,r", 500);
        gm.start(false);
        Assert.assertEquals(0, gm.nextPlayer().getUnusedArmies());
    }

}
