package model;


import model.contract.*;
import model.strategy.Aggressive;
import model.strategy.Defensive;
import model.strategy.Normal;
import util.Color;
import util.expetion.InvalidNumOfPlayersException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Observable;

import controller.LoggerController;


/**
 * This is the main game manager that controls the game
 * @author Amir
 * @version 0.1.0
 */
public class GameManager extends Model {

    private static int MIN_PLAYERS = 2;
    private static int MAX_PLAYERS = 6;

    private int numberOfPlayers = 0;
    private int turn = -1;

    private int strategyTurn = -1;
    ArrayList<IStrategy> strategies = new ArrayList<>();

    public CardDeck cardDeck = new CardDeck();

    private boolean isGameOn=false;

    private IMap map;
    private ArrayList<IPlayer> playerlist = new ArrayList<>();
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
        strategies.add(new Normal());
        strategies.add(new Defensive());
        strategies.add(new Aggressive());
    }


    /**
     * constructor
     * @param m selected map
     * @param players number of players
     */
    public GameManager(IMap m,int players) {

        this.numberOfPlayers = players;
        this.map = m;
        strategies.add(new Normal());
        strategies.add(new Defensive());
        strategies.add(new Aggressive());
    }

    /**
     * Start the game
     * @throws InvalidNumOfPlayersException be careful
     */
    public void start() throws InvalidNumOfPlayersException
    {
        this.setPhase("Startup");
        this.initGame();
        sendNotification("GamePlay", playerlist);
        this.isGameOn = true;
        this.setPhase("GamePlay");
        sendNotification("PhaseChange", "PhaseChange:Game Play");
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

            IPlayer winner = getWinner();
            if (winner == null)
            {
                LoggerController.log("No winner, so next turn will start.");
            }

            i++;
            if (winner!= null)
                this.isGameOn=false;
        }

        String dominationView = this.domitantionResult(true, i);
        LoggerController.log(dominationView);
        this.sendNotification(NotificationType.DominationView, dominationView);
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

        //Step 2: if player has occupied all the continent
        for(IContinent c: this.map.getContinents() )
        {
            boolean isKing = true;
            for(ITerritory t : c.getTerritories())
            {
                if (t.getOwner() != this)
                    isKing = false;
            }

            if (isKing)
                result = c.getContinentValue();
        }

        //Step 3: card exchanging
        if(p.getCardsSize() > 3)
        {
            int exchangeValue = 0;
            ArrayList<Card> cards = p.getCardSet();
            for(Card c : cards)
                cardDeck.returnCard(c);
            switch (p.getTrades())
            {
                case 1:
                    exchangeValue = 4;
                    p.increaseTrades();
                    break;
                case 2:
                    exchangeValue = 6;
                    p.increaseTrades();
                    break;
                case 3:
                    exchangeValue = 8;
                    p.increaseTrades();
                    break;
                case 4:
                    exchangeValue = 10;
                    p.increaseTrades();
                    break;
                case 5:
                    exchangeValue = 12;
                    p.increaseTrades();
                    break;
                case 6:
                    exchangeValue = 15;
                    p.increaseTrades();
                    break;
                default:
                    exchangeValue = 15 + (p.getTrades() - 6) * 5;
                    p.increaseTrades();
                    break;
            }

            LoggerController.log(String.format("%s received %s armies via card exchange(exchange no %s)", p.getName(),
                    exchangeValue, p.getTrades()));
            result += exchangeValue;

        }

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
            IStrategy strategy = getRandomStrategy();
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
            if((p.getTerritories().size() == 0) && !this.getPhase().equals("Startup"))
            {
                p.setStatus(false);
            }
        }

        IPlayer result=null;
        while(result==null)
        {
            if(this.turn == this.numberOfPlayers-1)
                turn = -1;
            turn++;
            IPlayer tmp = this.playerlist.get(turn);
            if (tmp.getStatus())
                result = tmp;
        }

        if(!getPhase().equals("Startup"))
        	sendNotification("GameChange", result.getName()+": Phase started");
        
       
        
        return result;
    }

    /**
     * reset the turn
     * @see GameManager#nextPlayer()
     */
    private void resetTurn() { this.turn = -1; }


    /**
     * what phase is the game in now
     * @return phase name
     */
    public String getPhase() { return this.currentPhase; }
    public void setPhase(String value) {
        this.currentPhase = value;
    }


    /**
     * Generate domination view string
     * @param verbos to generate texts or just calculate winner
     * @return domination view
     */
    public String domitantionResult(boolean verbos, int trn)
    {
        StringBuilder sb = new StringBuilder();
        if(verbos)
            sb.append(String.format("===DOMINATION VIEW AT TURN %s===\n", trn));

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
        		sb.append(String.format("%s(%s) controls %s of the map.\n", p.getName(), p.getStrategy().getName(), p.getDomination()));
        }

        sendNotification("DominationView", "DominationView: "+sb.toString());
        
        if(verbos)
            sb.append("=====================");

        return sb.toString();

    }


    /**
     * Determine the winner
     * @return player who won the game
     */
    public IPlayer getWinner()
    {
        IPlayer winner = null;
        this.domitantionResult(false,0);
        //if(this.playerlist.get(0).getDomination()>95.0)
        if(this.playerlist.get(0).getDomination()>45.0)
        {
            winner = this.playerlist.get(0);
        }

        return winner;

    }

    /**
     * get a random playing strategy
     * @return strategy to play the game
     */
    public IStrategy getRandomStrategy()
    {
        if(this.strategyTurn==this.strategies.size()-1)
            this.strategyTurn = -1;
        this.strategyTurn++;

        return this.strategies.get(strategyTurn);

    }



}
