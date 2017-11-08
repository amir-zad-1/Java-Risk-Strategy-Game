package soen6441team15.soen6441;

import model.GameManager;
import model.contract.IPlayer;
import org.junit.Assert;
import org.junit.Test;
import util.expetion.InvalidNumOfPlayersException;

/**
 * Start game without a map
 */
public class GpTest4 {

    @Test(expected = Exception.class)
    public void test() throws InvalidNumOfPlayersException
    {
        GameManager gm = new GameManager(3);
        gm.start(false);
    }

}
