package model;


import model.contract.IContinent;
import model.contract.IMap;
import model.contract.IPlayer;
import model.contract.ITerritory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.Color;
import util.Helpers;
import util.LogMessageEnum;
import util.expetion.InvalidNumOfPlayersException;
import view.Logger;

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
    public GameManager(IMap map, int players) {

        this.numberOfPlayers = players;
        this.map = map;


    }

    /**
     * Start the game
     * @throws InvalidNumOfPlayersException
     */
    public void start() throws InvalidNumOfPlayersException
    {
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
    public void initGame() throws InvalidNumOfPlayersException
    {
        //Step 1: Add players and give each them armies according to the rules
        Logger.log("====1. Adding Players====");
        addPlayers();

        //Step 2: Allocate initial armies according to the rules
        Logger.log("====2. Allocating Initial Armies====");
        allocateInitialArmies();

        //Step 3: Randomly allocate the countries in the map
        Logger.log("====3. Allocating Territories====");
        allocateTerritories();

        //Step 4: Place armies into territories in turn
        Logger.log("====4. Placing armies one by one into territories====");
        placeInitialArmies();

    }

    /**
     * this is the method that handles the game play
     */
    public void play()
    {
        this.resetTurn();
        int i = 1;
        Logger.log("====5. PLAYING====");
        while(this.isGameOn)
        {
            Logger.log(String.format("====Turn %s====", i));
            IPlayer p = nextPlayer();
            reinforcement(p);
            attack(p);
            fortification(p);
            i++;
            if (i==this.numberOfPlayers)
                this.isGameOn=false;
        }

    }


    /**
     * This will handle attack phase but not implemented yet
     * @param p
     */
    public void attack(IPlayer p)
    {
        //todo: Implement attach phase.
        Logger.log(String.format("============%s ATTACK STARTS===========", p.getName()));
        Logger.log(LogMessageEnum.WARNING, "Jump from attack phase. :)");
        Logger.log(String.format("============%s ATTACK DONE===========", p.getName()));
    }

    /**
     * does the fortification phase and randomly move armies to another territories
     * owned by the player
     * @param p player
     */
    public void fortification(IPlayer p)
    {
        Logger.log(String.format("============%s FORTIFICATION STARTS===========", p.getName()));

        ITerritory from = p.getRandomTerritory();
        ITerritory to;

        ArrayList<ITerritory> neighbours = from.getAdjacentTerritoryObjects();
        if(neighbours.size()>0)
            to = neighbours.get(0);
        else
            to = p.getRandomTerritory();

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
    public void reinforcement(IPlayer p)
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
    public int calculateReinforcementArmies(IPlayer p)
    {
        int result = 0;

        //Step 1: calculate based on occupied territories
        result += p.getTerritories().size() / 3;
        if(result<3)
            result = 3; // Since the minimum is 3 armies.

        return result;
    }


    /**
     * this method helps players to move armies from a territory to another
     * @param p player
     */
    public void placeArmies(IPlayer p)
    {
        int armiesToPlace = p.getUnusedArmies();
        int i = 0;
        while(i<armiesToPlace )
        {
            Logger.log(p.getState());
            ITerritory playerRandomTerritory = p.getRandomTerritory();
            int randomArmy = util.Helpers.getRandomInt(p.getUnusedArmies(),1);

            p.placeArmy(randomArmy, playerRandomTerritory);
            i += randomArmy;

            Logger.log(p.getState());
        }

    }

    /**
     * This method automatically place initial armies into territories one by one
     * according to the game rules
     */
    public void placeInitialArmies()
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

            ITerritory playerRandomTerritory  = p.getRandomTerritory();
            int randomArmy = 1;

            p.placeArmy(randomArmy, playerRandomTerritory  );
            i += randomArmy;

            Logger.log(p.getState());
        }

    }

    /**
     * this method add players to the game
     * it uses the number which is given while creating game instance.
     * @throws InvalidNumOfPlayersException
     */
    public void addPlayers() throws InvalidNumOfPlayersException {

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

    /**
     * calculates initial armies according to the game rules
     * @return number of armies
     */
    public int calculateInitialArmies()
    {
        int result = 0;

        switch (this.numberOfPlayers)
        {
            case 2:
                result = 40;
                break;
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

    /**
     * calculates initial armies by calling the appropriate method then
     * sets the return number for each player
     */
    public void allocateInitialArmies()
    {
        int initialArmies = calculateInitialArmies();
        for(IPlayer p : this.playerlist)
        {
            p.setUnusedArmies(initialArmies);
        }
        Logger.log(String.format("%s armies allocated to each player.", initialArmies));
    }


    /**
     * Randomly allocates territories to players
     */
    public void allocateTerritories()
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

    /**
     * returns next player based on turns
     * @return player object
     */
    public IPlayer nextPlayer()
    {
        if(this.turn == this.numberOfPlayers-1)
            turn = -1;
        turn++;
        return this.playerlist.get(turn);

    }

    /**
     * reset the turn
     * @see GameManager#nextPlayer()
     */
    private void resetTurn() { this.turn = -1; }

    

}
