package model;


import model.contract.*;
import model.strategy.Aggressive;
import model.strategy.Normal;
import util.Color;
import util.Helpers;
import util.LogMessageEnum;
import util.expetion.InvalidNumOfPlayersException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import controller.LoggerController;


/**
 * This is the main game manager that controls the game
 * @author Amir
 * @version 0.1.0
 */
public class GameManager extends Observable {

    private static int MIN_PLAYERS = 2;
    private static int MAX_PLAYERS = 6;

    private int numberOfPlayers = 0;
    private int turn = -1;

    private boolean isGameOn=false;

    private IMap map;
    private ArrayList<IPlayer> playerlist = new ArrayList<IPlayer>();
    private String currentPhase = "";


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
        this.setPhase("Startup");
        this.initGame();
        this.isGameOn = true;
        this.setPhase("GamePlay");
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

            this.setPhase(String.format("   %s Reinforcement", p.getName()));
            p.reinforcement();

            this.setPhase(String.format("   %s Attack", p.getName()));
            p.attack();

            this.setPhase(String.format("   %s Fortification", p.getName()));
            p.fortification();

            IPlayer winner = getWinner();
            if (winner == null)
            {
                LoggerController.log("No winner, so next turn will start.");
            }

            i++;
            //if (i==this.numberOfPlayers*3 || winner!= null)
            if (winner!= null)
                this.isGameOn=false;
        }

        LoggerController.log(this.domitantionResult(true));
        //LoggerController.log(this.getWinner().getName());

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
            IStrategy strategy = new Aggressive();
            IPlayer p = new Player("Player " + Integer.toString(i), colorManager.getRandomColor(), strategy);
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
        for(IPlayer p: this.playerlist)
        {
            if(p.getTerritories().size()==0 && this.getPhase() != "Startup" )
            {
                this.playerlist.remove(p);
            }
        }

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


    public String getPhase() { return this.currentPhase; }
    public void setPhase(String value) {
        this.currentPhase = value;
        this.setChanged();
        this.notifyObservers();
    }

    public String domitantionResult(boolean verbos)
    {
        StringBuilder sb = new StringBuilder();
        if(verbos)
            sb.append("===DOMINATION VIEW===\n");

        int total_territories = 0;
        for(IPlayer p:this.playerlist)
            total_territories+=p.getTerritories().size();

        for(IPlayer p:this.playerlist)
        {
            double control_percent = Math.round(((double) p.getTerritories().size() / (double) total_territories) * 100) ;
            p.setDomination(control_percent);
        }

        Collections.sort(this.playerlist);
        Collections.reverse(this.playerlist);

        if(verbos) {
        for(IPlayer p:this.playerlist)
            sb.append(String.format("%s controls %s of the map.\n", p.getName(), p.getDomination()));
        }

        if(verbos)
            sb.append("=====================");

        return sb.toString();

    }

    public IPlayer getWinner()
    {
        IPlayer winner = null;
        this.domitantionResult(false);
        if(this.playerlist.get(0).getDomination()>70.0)
//        if(this.playerlist.get(0).getDomination() > this.playerlist.get(1).getDomination())
            winner = this.playerlist.get(0);

        return winner;

    }

}
