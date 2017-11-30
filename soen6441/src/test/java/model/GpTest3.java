package model;
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

    /**
     * 3 palyers
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test
    public void test3_1() throws InvalidNumOfPlayersException
    {
        IMap m = new Map();
        GameManager gm = new GameManager(3, "r,r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(35, expected_armies);
    }

    /**
     * 2 palyers
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test
    public void test3_2() throws InvalidNumOfPlayersException
    {
        IMap m = new Map();
        GameManager gm = new GameManager(2,"r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(40, expected_armies);
    }

    /**
     * 4 palyers
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test
    public void test3_3() throws InvalidNumOfPlayersException
    {
        IMap m = new Map();
        GameManager gm = new GameManager(4,"r,r,r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(30, expected_armies);
    }

    /**
     * 5 palyers
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test
    public void test3_4() throws InvalidNumOfPlayersException
    {
        IMap m = new Map();
        GameManager gm = new GameManager(5,"r,r,r,r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(25, expected_armies);
    }

    /**
     * 6 palyers
     * @throws InvalidNumOfPlayersException if number of player not as per game rules
     */
    @Test
    public void test3_5() throws InvalidNumOfPlayersException
    {
        IMap m = new Map();
        GameManager gm = new GameManager(6, "r,r,r,r,r,r", 500);
        int expected_armies = gm.calculateInitialArmies();
        Assert.assertEquals(20, expected_armies);
    }


}
