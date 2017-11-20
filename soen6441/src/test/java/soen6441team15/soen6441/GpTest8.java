package soen6441team15.soen6441;

import model.GameManager;
import model.Map;
import model.contract.IMap;
import model.contract.IPlayer;
import model.contract.ITerritory;
import org.junit.Assert;
import org.junit.Test;
import util.ActionResponse;
import util.expetion.InvalidNumOfPlayersException;


/**
 * Tests moving all armies form a territory
 */
public class GpTest8 {

    @Test()
    public void test() throws InvalidNumOfPlayersException
    {
        IMap m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3,"r,r,r", 500);
        gm.start(false);

        IPlayer p = gm.nextPlayer();
        ITerritory from = p.getRandomTerritory();
        ITerritory to = p.getRandomTerritory();
        ActionResponse result = p.moveArmies(from, to, from.getArmies());

        Assert.assertFalse(result.getOk());

    }

}
