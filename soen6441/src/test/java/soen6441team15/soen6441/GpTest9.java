package soen6441team15.soen6441;

import model.GameManager;
import model.Map;
import model.contract.IMap;
import model.contract.IPlayer;
import org.junit.Assert;
import org.junit.Test;
import util.expetion.InvalidNumOfPlayersException;


/**
 * testing card exchange logic
 */
public class GpTest9 {

    @Test()
    public void test1() throws InvalidNumOfPlayersException {
        IMap m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3);
        gm.start(false);

        IPlayer p = gm.nextPlayer();
        int exchangeValue = gm.exchangeCard(p);
        Assert.assertEquals(0, exchangeValue);


    }

//    @Test()
//    public void test2() throws InvalidNumOfPlayersException {
//        IMap m = new Map();
//        m.clearData();
//        m.fakeData();
//
//        GameManager gm = new GameManager(m, 3);
//        gm.start(false);
//
//        IPlayer p = gm.nextPlayer();
//        int exchangeValue = gm.exchangeCard(p);
//        Assert.assertEquals(0, exchangeValue);
//
//
//    }

}
