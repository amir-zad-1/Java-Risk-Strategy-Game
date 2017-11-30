package model;

import model.AttackPlan;
import model.GameManager;
import model.Map;
import model.contract.IMap;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;
import model.strategy.Aggressive;
import model.strategy.Benevolent;
import model.strategy.Random;
import org.junit.Test;
import org.junit.Assert;
import util.expetion.InvalidNumOfPlayersException;

/**
 * this class tests all strategies to make sure they are correct
 */
public class StrategiesTest {


    /**
     * this will test attack numbers by benevolent strategy
     */
    @Test
    public void Test1()
    {
        IStrategy s = new Benevolent();
        int attacks = s.getAttackAttempts();
        Assert.assertEquals(0, attacks);
    }

    /**
     * checks the dice to attack
     */
    @Test
    public void Test2()
    {
        IStrategy s = new Benevolent();
        int attacks = s.diceToAttack(null);
        Assert.assertEquals(0, attacks);
    }


    /**
     * checks the dice to defend
     */
    @Test
    public void Test3()
    {
        IStrategy s = new Benevolent();
        int attacks = s.diceToDefend(null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * test fortification armies
     */
    @Test
    public void Test4()
    {
        IStrategy s = new Benevolent();
        int attacks = s.getFortificationArmies(null, null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * test fortification territory from
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test
    public void Test5() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3, "r,r,r", 500);
        gm.start(false);

        IPlayer p = gm.nextPlayer();
        IStrategy s = new Benevolent();
        p.setStrategy(s);
        ITerritory from = s.getFortificationFromTerritory(p);
        Assert.assertNotNull(from);
    }


    /**
     * test fortification territory to
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test
    public void Test6() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3, "r,r,r", 500);
        gm.start(false);

        IPlayer p = gm.nextPlayer();
        IStrategy s = new Benevolent();
        p.setStrategy(s);
        ITerritory from = s.getFortificationFromTerritory(p);
        ITerritory to = s.getFortificationToTerritory(p, from);
        Assert.assertNotNull(to);
    }

    /**
     * tests attack plan
     */
    @Test
    public void Test7()
    {
        IStrategy s = new Benevolent();
        AttackPlan attack = s.getAttackPlan(null);
        Assert.assertNull(attack);
    }

    /**
     * this will test attack numbers by aggressive strategy
     */
    @Test
    public void Test8()
    {
        IStrategy s = new Aggressive();
        int attacks = s.getAttackAttempts();
        Assert.assertEquals(5, attacks);
    }

    /**
     * checks the dice to attack
     */
    @Test
    public void Test9()
    {
        IStrategy s = new Aggressive();
        int attacks = s.diceToAttack(null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * checks the dice to defend
     */
    @Test
    public void Test10()
    {
        IStrategy s = new Aggressive();
        int attacks = s.diceToDefend(null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * test fortification territory from aggressive
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test
    public void Test11() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3, "r,r,r", 500);
        gm.start(false);

        IPlayer p = gm.nextPlayer();
        IStrategy s = new Aggressive();
        p.setStrategy(s);
        ITerritory from = s.getFortificationFromTerritory(p);
        Assert.assertNotNull(from);
    }


    /**
     * test fortification territory to
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test
    public void Test12() throws InvalidNumOfPlayersException
    {
        Map m = new Map();
        m.clearData();
        m.fakeData();

        GameManager gm = new GameManager(m, 3, "r,r,r", 500);
        gm.start(false);

        IPlayer p = gm.nextPlayer();
        IStrategy s = new Aggressive();
        p.setStrategy(s);
        ITerritory from = s.getFortificationFromTerritory(p);
        ITerritory to = s.getFortificationToTerritory(p, from);
        Assert.assertNotNull(to);
    }


    /**
     * this will test attack numbers by benevolent strategy
     * 
     */
    @Test
    public void Test13()
    {
        IStrategy s = new Random();
        int attacks = s.getAttackAttempts();
        Assert.assertNotEquals(0, attacks);
    }

    /**
     * checks the dice to attack
     */
    @Test
    public void Test14()
    {
        IStrategy s = new Random();
        int attacks = s.diceToAttack(null);
        Assert.assertNotEquals(0, attacks);
    }


    /**
     * checks the dice to defend
     */
    @Test
    public void Test15()
    {
        IStrategy s = new Random();
        int attacks = s.diceToDefend(null);
        Assert.assertNotEquals(0, attacks);
    }


}
