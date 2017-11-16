package model;

import model.AttackPlan;
import model.GameManager;
import model.Map;
import model.contract.IMap;
import model.contract.IPlayer;
import model.contract.ITerritory;
import org.junit.Assert;
import org.junit.Test;
import util.expetion.InvalidNumOfPlayersException;

/**
 * testing attack logic
 */
public class GpTest10 {

    /**
     * There should be no territory to attack
     * @throws InvalidNumOfPlayersException is not as per game rules
     */
    @Test()
    public void test1() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3,"r,r,r", 500);
        gm.start(false);

        IPlayer p = gm.nextPlayer();
        AttackPlan ap = p.getTerritoryToAttack();
        Assert.assertNull(ap);

    }

    /**
     * checks if attack from territory is owned by the attacker
     * @throws InvalidNumOfPlayersException is number of players not as per game rules
     */
    @Test()
    public void test2() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3,"r,r,r", 500);
        gm.start(false);

        IPlayer p1 = gm.nextPlayer();
        IPlayer p2 = gm.nextPlayer();

        AttackPlan ap = new AttackPlan(p1.getRandomTerritory(), p2.getRandomTerritory());
        Assert.assertTrue(ap.getFrom().getOwner() == p1);

    }


    /**
     * checks if attack to territory is owned by the defender
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test()
    public void test3() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3,"r,r,r", 500);
        gm.start(false);

        IPlayer p1 = gm.nextPlayer();
        IPlayer p2 = gm.nextPlayer();

        AttackPlan ap = new AttackPlan(p1.getRandomTerritory(), p2.getRandomTerritory());
        Assert.assertTrue(ap.getTo().getOwner() == p2);

    }

    /**
     * checks if attacker and defender are not the same
     * @throws InvalidNumOfPlayersException is number of player not as per game rules
     */
    @Test()
    public void test4() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3,"r,r,r", 500);
        gm.start(false);

        IPlayer p1 = gm.nextPlayer();
        IPlayer p2 = gm.nextPlayer();

        AttackPlan ap = new AttackPlan(p1.getRandomTerritory(), p2.getRandomTerritory());
        Assert.assertTrue(p1 != p2);

    }

//    /**
//     * checks if attacker and defender are not the same
//     * @throws InvalidNumOfPlayersException
//     */
//    @Test()
//    public void test5() throws InvalidNumOfPlayersException
//    {
//        IMap m = new Map();
//        m.clearData();
//        m.fakeData();
//
//        GameManager gm = new GameManager(m, 3,"r,r,r", 500);
//        gm.start(false);
//
//        IPlayer p1 = gm.nextPlayer();
//        IPlayer p2 = gm.nextPlayer();
//
//        //AttackPlan ap = new AttackPlan(p1.getRandomTerritory(), p2.getRandomTerritory());
//        //Assert.assertTrue(p1 != p2);
//
//    }

}
