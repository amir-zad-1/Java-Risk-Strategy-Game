package model;


import model.contract.IContinent;
import model.contract.IMap;
import model.contract.IPlayer;
import model.contract.ITerritory;
import util.Color;
import util.Helpers;
import util.LogMessageEnum;
import util.expetion.InvalidNumOfPlayersException;

import java.util.ArrayList;

import controller.LoggerController;


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
     * @param players number of players
     * @throws InvalidNumOfPlayersException be careful
     */
    public GameManager(int players) {

        this.numberOfPlayers = players;
        this.map = new Map();
    }
    
    
    public GameManager(IMap m,int players) {

        this.numberOfPlayers = players;
        this.map = m;

    }

    /**
     * Start the game
     * @throws InvalidNumOfPlayersException be careful
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
     * @throws InvalidNumOfPlayersException be careful
     */
    public void initGame() throws InvalidNumOfPlayersException
    {
        //Step 1: Add players and give each them armies according to the rules
        LoggerController.log("====1. Adding Players====");
        addPlayers();

        //Step 2: Allocate initial armies according to the rules
        LoggerController.log("====2. Allocating Initial Armies====");
        allocateInitialArmies();

        //Step 3: Randomly allocate the countries in the map
        LoggerController.log("====3. Allocating Territories====");
        allocateTerritories();

        //Step 4: Place armies into territories in turn
        LoggerController.log("====4. Placing armies one by one into territories====");
        placeInitialArmies();

    }

    /**
     * this is the method that handles the game play
     */
    public void play()
    {
        this.resetTurn();
        int i = 1;
        LoggerController.log("====5. PLAYING====");
        while(this.isGameOn)
        {
            LoggerController.log(String.format("====Turn %s====", i));
            IPlayer p = nextPlayer();
            p.reinforcement();
            p.attack();
            p.fortification();
            i++;
            if (i==this.numberOfPlayers)
                this.isGameOn=false;
        }

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
            LoggerController.log(p.getState());
            ITerritory playerRandomTerritory = p.getRandomTerritory();
            int randomArmy = util.Helpers.getRandomInt(p.getUnusedArmies(),1);

            p.placeArmy(randomArmy, playerRandomTerritory);
            i += randomArmy;

            LoggerController.log(p.getState());
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

            LoggerController.log(p.getState());

            ITerritory playerRandomTerritory  = p.getRandomTerritory();
            int randomArmy = 1;

            p.placeArmy(randomArmy, playerRandomTerritory  );
            i += randomArmy;

            LoggerController.log(p.getState());
        }

    }

    /**
     * this method add players to the game
     * it uses the number which is given while creating game instance.
     * @throws InvalidNumOfPlayersException be careful
     */
    public void addPlayers() throws InvalidNumOfPlayersException {

        if (this.numberOfPlayers > MAX_PLAYERS || this.numberOfPlayers < MIN_PLAYERS)
            throw new InvalidNumOfPlayersException();


        Color colorManager = new Color();
        for (int i=1; i<=this.numberOfPlayers; i++) {
            IPlayer p = new Player("Player " + Integer.toString(i), colorManager.getRandomColor());
            p.setGameManager(this);
            this.playerlist.add(p);
            LoggerController.log(p.toString() + " was added to the game.");
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
        LoggerController.log(String.format("%s armies allocated to each player.", initialArmies));
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
