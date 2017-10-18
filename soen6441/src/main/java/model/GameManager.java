package model;


import model.contract.IContinent;
import model.contract.IMap;
import model.contract.IPlayer;
import model.contract.ITerritory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.Color;
import util.Helpers;
import util.LogMessageEnum;
import util.Logger;
import util.expetion.InvalidNumOfPlayersException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main game manager that controls the game
 * @author Amir
 * @version 0.1.0
 */
public class GameManager {

    private static int MIN_PLAYERS = 2;
    private static int MAX_PLAYERS = 6;

    private int numberOfPlayers = 0;
    private int turn = -1;

    private boolean isGameOn=false;

    private IMap map;
    private ArrayList<IPlayer> playerlist = new ArrayList<IPlayer>();


    /**
     * Class constructor.
     * It sets the map and players field then
     * calls initGame that to add Players then
     * allocate default armies to each player then
     * allocate countries randomly to players
     * finally game is started
     * @param map the map file containing game map
     * @param players number of players
     * @throws InvalidNumOfPlayersException
     */
    public GameManager(IMap map, int players) throws InvalidNumOfPlayersException {

        this.numberOfPlayers = players;
        this.map = map;

        this.initGame();
        this.isGameOn = true;
        this.play();
    }

    /**
     * Initialize the game steps
     * Step 1: Add players and give each them armies according to the rules
     * Step 2: Randomly allocate the countries in the map
     * Step 3: Allocate initial armies according to the rules
     * Step 4: Place armies onto territories in turn
     * @throws InvalidNumOfPlayersException
     */
    private void initGame() throws InvalidNumOfPlayersException
    {
        //Step 1: Add players and give each them armies according to the rules
        addPlayers();

        //Step 2: Allocate initial armies according to the rules
        allocateInitialArmies();

        //Step 3: Randomly allocate the countries in the map
        allocateTerritories();

        //Step 4: Place armies into territories in turn
        placeInitialArmies();

    }

    /**
     * this is the method that handles the game play
     */
    private void play()
    {
        this.resetTurn();
        int i = 0;

        while(this.isGameOn)
        {
            IPlayer p = nextPlayer();
            reinforcement(p);
            attack(p);
            fortification(p);
            i++;
            if (i==1)
                this.isGameOn=false;
        }

    }


    /**
     * This will handle attack phase but not implemented yet
     * @param p
     */
    private void attack(IPlayer p)
    {
        //todo: Implement attach phase.
        Logger.log(String.format("============%s ATTACK STARTS===========", p.getName()));
        Logger.log(LogMessageEnum.WARNING, "Jump from attack phase. :)");
        Logger.log(String.format("============%s ATTACK DONE===========", p.getName()));
    }

    private void fortification(IPlayer p)
    {
        Logger.log(String.format("============%s FORTIFICATION STARTS===========", p.getName()));

        ITerritory from = p.getRandomTerritory();
        ITerritory to = p.getRandomTerritory();
        int number = Helpers.getRandomInt(from.getArmies(),1);
        p.moveArmies(from, to, number);


        Logger.log(String.format("============%s FORTIFICATION DONE===========", p.getName()));
    }

    /**
     * Handles reinforcement phase by :
     * calculating the number of armies each player should get
     * let the given player decide where to place the given armies
     * @param p the player that should do the reinforcement
     */
    private void reinforcement(IPlayer p)
    {
        Logger.log(String.format("============%s REINFORCEMENT STARTS===========", p.getName()));

        //Step 1: Reinforcement
        int newArmies = calculateReinforcementArmies(p);
        p.setUnusedArmies(newArmies);

        //Step 2: Place armies
        this.placeArmies(p);
        Logger.log(String.format("============%s REINFORCEMENT DONE===========", p.getName()));
    }

    /**
     * calculates reinforcement armies for each player
     * @param p player
     * @return number or armies the player should get
     */
    private int calculateReinforcementArmies(IPlayer p)
    {
        int result = 0;

        //Step 1: calculate based on occupied territories
        result += p.getTerritories().size() / 3;
        if(result<3)
            result = 3; //Since the minimum is 3 armies.

        //Step 2: in addition based on controlled continents
        //for(IContinent c:this.map.getContinents())
        //{
            //Logger.log(c.controlByOnePlayer()==null);
        //}
        return result;
    }

    private void placeArmies(IPlayer p)
    {
        int armiesToPlace = p.getUnusedArmies();
        int i = 0;
        while(i<armiesToPlace )
        {
            Logger.log(p.getState());
//            Scanner sc = new Scanner(System.in);
//            Logger.log(String.format("\n%s, place the desired number of armies on your territory. (e.g Russia,1):", p.getName()));
//            String n = sc.nextLine();
//            String[] inp = n.split(",");
//
//            int r = Integer.parseInt(inp[1]);
//
//            ITerritory t = p.getTerritoryByName(inp[0]);
            ITerritory playerRandomTerritory = p.getRandomTerritory();
            int randomArmy = util.Helpers.getRandomInt(p.getUnusedArmies(),1);

            p.placeArmy(randomArmy, playerRandomTerritory);
            i += randomArmy;

            Logger.log(p.getState());
        }

    }

    private void placeInitialArmies()
    {

        int armiesToPlace = 0;
        for(IPlayer x:this.playerlist)
            armiesToPlace+=x.getUnusedArmies();

        int i = 0;
        while(i<armiesToPlace )
        {
            IPlayer p = nextPlayer();
            if(p.getUnusedArmies()==0)
                continue;

            Logger.log(p.getState());
            //Scanner sc = new Scanner(System.in);
            //Logger.log(String.format("\n%s, place the desired number of armies on your territory. (e.g Russia,1):", p.getName()));
            //String n = sc.nextLine();
            //String[] inp = n.split(",");
            //int r = Integer.parseInt(inp[1]);

            ITerritory playerRandomTerritory  = p.getRandomTerritory();
            int randomArmy = util.Helpers.getRandomInt(p.getUnusedArmies(),1);

            p.placeArmy(randomArmy, playerRandomTerritory  );
            i += randomArmy;

            Logger.log(p.getState());
        }

    }

    private void addPlayers() throws InvalidNumOfPlayersException {

        if (this.numberOfPlayers > MAX_PLAYERS || this.numberOfPlayers < MIN_PLAYERS)
            throw new InvalidNumOfPlayersException();


        Color colorManager = new Color();
        for (int i=1; i<=this.numberOfPlayers; i++) {
            IPlayer p = new Player("Player " + Integer.toString(i), colorManager.getRandomColor());
            this.playerlist.add(p);
            Logger.log(p.toString() + " was added to the game.");
        }
        colorManager = null;
    }

    private int calculateInitialArmies()
    {
        int result = 0;

        switch (this.numberOfPlayers)
        {
            case 2:
                result = 0;
                //todo: not implemented
                throw new NotImplementedException();
            case 3:
                result = 35;
                break;
            case 4:
                result = 30;
                break;
            case 5:
                result = 25;
                break;
            case 6:
                result = 20;
                break;
        }

        return result;
    }

    private void allocateInitialArmies()
    {
        int initialArmies = calculateInitialArmies();
        for(IPlayer p : this.playerlist)
        {
            p.setUnusedArmies(initialArmies);
        }
    }

    private void allocateTerritories()
    {

        for(IContinent c:this.map.getContinents())
        {
            for (ITerritory t: c.getTerritories())
            {
                IPlayer p = this.nextPlayer();
                p.ownTerritory(t);
            }
        }
    }

    public IPlayer nextPlayer()
    {
        if(this.turn == this.numberOfPlayers-1)
            turn = -1;
        turn++;
        return this.playerlist.get(turn);

    }

    private void resetTurn() { this.turn = -1; }

    public static void main(String[]  args){

        try
        {
            IMap m = new Map("/home/tony/Workspace/6441/projectFinal/6441-project/world.map");
            GameManager gm = new GameManager(m, 3);
        }
        catch (InvalidNumOfPlayersException e)
        {
            Logger.log(LogMessageEnum.ERROT, e.getMessage());
        }
    }

}
