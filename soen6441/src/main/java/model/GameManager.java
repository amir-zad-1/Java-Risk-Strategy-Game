package model;


import model.contract.*;
import model.strategy.*;
import util.Color;
import util.expetion.InvalidNumOfPlayersException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Scanner;

import controller.LoggerController;


/**
 * This is the main game manager that controls the game
 * @author Amir
 * @version 0.1.0
 */
public class GameManager extends Observable {

    
	/**
	 * {@link #playerlist} points to the current turn of the player
	 */
	int playerCursor = 0;
	
    IPlayer temporarayPlyaerholder;
    
	private static int MIN_PLAYERS = 2;
    private static int MAX_PLAYERS = 6;
    
    private int numberOfPlayers = 0;
    private int turn = -1;
    private String name = "Game";
    private String playersText = "";

    private int strategyTurn = -1;
    private String strategyString = "";
    ArrayList<IStrategy> strategies = new ArrayList<>();

    public CardDeck cardDeck = new CardDeck();
    public int turns = 500;

    private boolean isGameOn=false;

    private IMap map;
    private ArrayList<IPlayer> playerlist = new ArrayList<>();
    private String currentPhase = "";


    /**
     * It sets the map and players field then
     * calls initGame that to add Players then
     * allocate default armies to each player then
     * allocate countries randomly to players
     * finally game is started
     * @param players number of players
     * @throws InvalidNumOfPlayersException be careful
     */
    public void startGame(int players, String strategies) throws InvalidNumOfPlayersException {

        this.numberOfPlayers = players;
        this.strategyString  = strategies;
        this.setStrategies();
        this.map = new Map();
        start();
    }

    
    /**
     * Class constructor.
     * It sets the map and players field then
     * calls initGame that to add Players then
     * allocate default armies to each player then
     * allocate countries randomly to players
     * finally game is started
     * @param players number of players
     * @exception InvalidNumOfPlayersException be careful
     */
    public GameManager(int players, String strategies, int totalTurns) {

        this.numberOfPlayers = players;
        this.map = new Map();
        this.strategyString  = strategies;
        this.setStrategies();
        this.turns = totalTurns;
    }

    

    /**
     * Constructor to initialize the game
     * @param m is selected map
     * @param players number of players that user choose
     */
    public GameManager(IMap m,int players, String strategies, int totalTurns) {

        this.numberOfPlayers = players;
        this.map = m;
        this.strategyString = strategies;
        this.setStrategies();
        this.turns = totalTurns;
    }

    /**
	 * Empty Constructor to initialize the GameManager in Driver class
	 */
	public GameManager() {
		
	}

    /**
     * set startegies according to strategies string
     * sample for 3 players with Human, Random and Aggressive strategies is h,r,a
     */
	public void setStrategies()
    {
        String[] stra = this.strategyString.split(",");
        for(String s:stra)
        {
            switch (s)
            {
                case "h":
                    this.strategies.add(new Human());
                    break;
                case "a":
                    this.strategies.add(new Aggressive());
                    break;
                case "b":
                    this.strategies.add(new Benevolent());
                    break;
                case "r":
                    this.strategies.add(new Random());
                    break;
                case "c":
                    this.strategies.add(new Cheater());
                    break;
            }
        }
    }

	/**
     * Start the game
     * @throws InvalidNumOfPlayersException be careful
     */
    public void start() throws InvalidNumOfPlayersException
    {
    	sendNotification("GameChange: StartUp");
    	this.setPhase("Startup");
        this.initGame();
        sendStartupEnd();
        this.isGameOn = true;
        this.setPhase("GamePlay");
        sendNotification("GameChange: StartUp phase finished \n Game Play is about to start");
        //this.resetTurn();
        this.play(true);
    }


    /**
     * to start the game
     * @param play should they start the game
     * @throws InvalidNumOfPlayersException is not provided as per game rules
     */
    public void start(boolean play) throws InvalidNumOfPlayersException
    {
        this.setPhase("Startup");
        this.initGame();
        this.isGameOn = true;
        this.setPhase("GamePlay");
        if (play)
            this.play(true);
    }

    /**
	 * Notifies all the player object observers that startUp phase is completed
	 */
	private void sendStartupEnd() {
		for(IPlayer p : playerlist){
			p.sendNotify(p.getState());
		}		
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
        initPlayers();

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
     * This method moves the player to the next phase whenever it get called
     * If a player finished his turn 3 phases then selects next player 
     */
    public void takeNextTurn() {
    	if(playerCursor ==0){
    		temporarayPlyaerholder = nextPlayer();
    	}else if(playerCursor == 1){
    		temporarayPlyaerholder.sendNotify("CardView: Start showing");
    		temporarayPlyaerholder.reinforcement();
    	}else if(playerCursor == 2){
    		 temporarayPlyaerholder.attack();
    	}else if(playerCursor == 3){
    		temporarayPlyaerholder.fortification();
    	}
    	
    	playerCursor++; 
    	if(playerCursor == 4){
    		playerCursor = 0;
    	}
    	this.domitantionResult();
	}
    
    
    /**
     * This method calculates the domination of each player and 
     * sends it to the Observers
     */
    public void domitantionResult(){
    	String tmp = "";
    	 for(IPlayer p:this.playerlist)
         {
             double control_percent = Math.round(((double) p.getTerritories().size() / Map.totalnumberOfTerritories) * 100);
             tmp += "\n"+ p.getName()+"="+control_percent;
         }
    	 sendNotification("DominationView: "+tmp);
    }
    
    
    /**
     * this is the method that handles the game play
     */
    public GameResult play(boolean verbos)
    {
        IPlayer winner = null;
        this.resetTurn();
        int i = 1;
        if(verbos)
            LoggerController.log("====5. PLAYING====");
        while(this.isGameOn)
        {
            if(verbos)
                LoggerController.log(String.format("====Turn %s====", i));
            IPlayer p = nextPlayer();
            
            p.reinforcement();
            
            p.attack();

            p.fortification();

            winner = getWinner();
            if (winner == null)
            {
                LoggerController.log("No winner, so next turn will start.");
            }

            i++;
            if (winner!= null || i == turns)
                this.isGameOn=false;
        }

        if (verbos){
            String dominationView = this.domitantionResult(true, i);
            LoggerController.log(dominationView);
        }
        String winnerName = "Draw";
        if (winner != null)
            winnerName = winner.getStrategy().getName();
        return new GameResult(map.getName(), winnerName);
 
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
                result += c.getContinentValue();
        }

        //Step 3: card exchanging
        result += exchangeCard(p);
       

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
            ITerritory playerRandomTerritory = p.getStrategy().getInforcementTerritory(p);
            int randomArmy = p.getStrategy().getReinforcementArmies(p);

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
    public void initPlayers() throws InvalidNumOfPlayersException {

        if (this.numberOfPlayers > MAX_PLAYERS || this.numberOfPlayers < MIN_PLAYERS)
            throw new InvalidNumOfPlayersException();
  

        Color colorManager = new Color();
        if(this.playerlist.size() == 0){
        	for (int i=1; i<=this.numberOfPlayers; i++) {
                IStrategy strategy = getRandomStrategy();
                IPlayer p = new Player("Player " + Integer.toString(i), colorManager.getRandomColor(), strategy);
                this.playersText += p.getStrategy().getName() +", ";
                p.setGameManager(this);
                this.playerlist.add(p);
                LoggerController.log(p.toString() + " was added to the game.");
        	}	
        }else{
        	for (int i=0; i<this.playerlist.size(); i++) {
        		IStrategy strategy = getRandomStrategy(); 
        		playerlist.get(i).setStrategy(strategy);
        		playerlist.get(i).setColor( colorManager.getRandomColor());
                this.playersText += playerlist.get(i).getStrategy().getName() +", ";
        		playerlist.get(i).setGameManager(this);
        		LoggerController.log(playerlist.get(i).toString() + " was added to the game.");
        	}
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
        	sendNotification("GameChange: "+result.getName()+" Turn started");
        
       
        
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

    
    public int exchangeCard(IPlayer p)
    {
        int exchangeValue = 0;

        if(p.getCardsSize() > 3)
        {
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
        }
        return exchangeValue;
    }


    /**
     * Generate domination view string
     * @param verbos to generate texts or just calculate winner
     * @return domination view
     * @return trn tells the turn number of the play
     */
    public String domitantionResult(boolean verbos, int trn)
    {
        StringBuilder sb = new StringBuilder();
        if(verbos)
            sb.append(String.format("===DOMINATION VIEW AT TURN %s===\n", trn));

        int total_territories = 0;
        for(IPlayer p:this.playerlist)
            total_territories+=p.getTerritories().size();

        ArrayList<IPlayer> tmp = new ArrayList<>();
        for(IPlayer p:this.playerlist)
        {
            double control_percent = Math.round(((double) p.getTerritories().size() / (double) total_territories) * 100) ;
            p.setDomination(control_percent);
            tmp.add(p);
        }

        Collections.sort(tmp);
        Collections.reverse(tmp);

        if(verbos) {
        	for(IPlayer p:tmp)
        		sb.append(String.format("%s(%s) controls %s of the map.\n", p.getName(), p.getStrategy().getName(), p.getDomination()));
        }

        sendNotification("DominationView: "+sb.toString());
        
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

        for(IPlayer p : this.playerlist)
            //if(p.getDomination()>85.0)
            if(p.getDomination()>90.0)
            {
                winner = p;
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



	/**
	 * Adds a player to the game
	 * @param newPlayer is a {@link Player}
	 */
	public void addPlayer(IPlayer newPlayer) {
		this.playerlist.add(newPlayer);
		
	}


	 /**
     * return list of continents controlled by the player
     * @param p player
     * @return list of continents
     */
    public ArrayList<IContinent> ContinentControlledBy(IPlayer p)
    {
        ArrayList<IContinent> result = new ArrayList<>();
        boolean isKing = true;
        for(IContinent c: this.map.getContinents() )
        {
            for(ITerritory t : c.getTerritories())
            {
                if (t.getOwner() != p)
                    isKing = false;
            }
            if (isKing)
                result.add(c);
        }
        return result;
}
	
	/**
	 * This method notifies all Observer about the update
	 * @param type is the type of notification
	 */
	private void sendNotification(String type) {
		setChanged();
		notifyObservers(type);				
	}


	public static void main(String[] args)
    {
        IMap m = new Map();
        m.clearData();
        m.fakeData();

        ArrayList<Map> maps = new ArrayList<>();
        maps.add((Map) m);


        GameManager gm = new GameManager(m, 3,"r,r,r", 5);
        try
        {
            gm.start(false);
            gm.play(true);
        }
        catch (Exception e)
        {

        }

        //Tournament t = new Tournament();
        //t.start(maps, 3, "c,r,r", 3,70);
    }

    /**
     * find a territory by its name
     * @param name name
     * @return the territory
     */
    public ITerritory getTerritory(String name)
    {
        ITerritory res = null;
        for (IContinent c : map.getContinents())
        {
            for(ITerritory t : c.getTerritories())
            {
                if(t.getName().equals(name))
                {
                    res = t;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * set game name
     * @param name name
     */
    public void setName(String name) { this.name = name; }

    /**
     * return name of the game
     * @return name
     */
    public String getName() { return this.name; }

    /**
     * returns players
     * @return player strategies
     */
    public String getPlayersText() { return this.playersText; }


}
