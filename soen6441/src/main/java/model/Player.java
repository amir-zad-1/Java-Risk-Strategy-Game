package model;


import model.contract.IStrategy;
import model.contract.ITerritory;
import model.contract.IPlayer;
import util.ActionResponse;
import util.Color;
import util.LogMessageEnum;
import view.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

/**
 * This is player POJO Object
 * @author Amir
 * @version 0.1.0
 */
public class Player extends Observable implements IPlayer, Comparable<IPlayer>, Serializable {

	/**
	 * {@link #serialVersionUID} used during deserialization to verify that the sender and receiver 
	 * of a serialized object have loaded classes for that object that are compatible with respect to 
	 * serialization. If the receiver has loaded a class for the object that has a different {@link #serialVersionUID}
	 */
	private static final long serialVersionUID = 1012842278301009514L;
	
	/** name of the player */
    private String name;
    /** holds the color of the player */
    private Color color;
    
    /** tells used and unused armies */
    private int unusedArmies = 0;
    private int usedArmies = 0;
    
    /** holds territories owned by the player*/
    private ArrayList<ITerritory> territories;
    
    /** holds the cards got for the player */ 
    private ArrayList<Card> cards = new ArrayList<>();
    
    /** holds the game manager of this player */
    private GameManager gm;
    
    /** holds how much percentage the territories he own on the map */
    private double domination = 0.0;
    
    
    /** holds the strategy of the player */
    IStrategy strategy;
    
    /** holds the status message of the player */
    private String statusMessage;
    
    /** tells if player is active */
	private boolean isActive = true;
    private int trades = 1;

    /**
     * Constructor that initializes the player details
     * @param name  player name
     * @param color player color
     * @param strategy is the strategy the player choose
     */
    public Player(String name, Color color, IStrategy strategy){
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
        this.strategy = strategy;
    }

    /**
     * Constructor that initializes the player object with only his name
	 * @param playerName is the name of the player
	 */
	public Player(String playerName) {
		this.name = playerName;
		this.territories = new ArrayList<>();
	}

	/**
     * @return the player name
     */
    @Override
    public String getName(){
        return this.name;
    }

    /**
     *This method is used to set the player name 
     * @param newName new name for the  player
     */
    @Override
    public void setName(String newName){
        this.name = newName;
    }
    
   

    /**
     * get how many percent of the world is controlled by the player
     * @return percentage
     */
    @Override
    public double getDomination() {
        return this.domination;
    }

    /**
     * set how many percent of the world is controlled by the player
     * @param value the percentage of map occupied by the player
     */
    @Override
    public void setDomination(double value) {
        this.domination = value;
    }


    /**
     * set number of unused armies for player
     * @param armies number of new armies
     */
    @Override
    public void setUnusedArmies(int armies) { this.unusedArmies = armies; }

    /**
     * set number of unused armies for player
     */
    @Override
    public int getUnusedArmies(){ return this.unusedArmies; }

    /**
     * method is used to set the armies
     * @param armies number or unused armies to be set
     */
    @Override
    public void setUsedArmies(int armies) { this.usedArmies = armies; }

    /**
     * sets number of new armies
     * @return new armies
     */
    @Override
    public int getUsedArmies(){ return this.usedArmies; }

    
    /**
     * this methos is used to set the color of the player
     * @param color is the new color for the player
     */
    public void setColor(Color color){ this.color = color; }

    /**
     * @return player's color
     */
    public Color getColor() { return this.color; }

    /**
     * Called if a player owns a territory
     * @param territory territory to be owned
     * @return if the operation was successful or not as {@link ActionResponse}
     */
    @Override
    public ActionResponse ownTerritory(ITerritory territory) {
    	
    	if(territory.getOwner() != null){
    	 sendNotification(territory.getOwner().getName()+ ": lost "+ territory.getName()+" because of "+this.getName());
    	}
    	territory.setOwner(this);
        this.placeArmy(1, territory);
        //as he own add it to his territories list
        this.territories.add(territory);
        this.statusMessage = String.format("%s owns %s", this.getName(),territory.getName());
        sendNotify();
        return new ActionResponse(true,  statusMessage);
    }

    /**
     * called when a player lose the territory
     * @param territory territory to be removed as {@link ActionResponse}
     * @return if the operation was successful
     */
    @Override
    public ActionResponse lostTerritory(ITerritory territory) {
    	this.territories.remove(territory);
    	this.statusMessage = String.format("%s lost %s", this.getName(),territory.getName());
    	sendNotify();
        return new ActionResponse(true, this.statusMessage);
    }

    /**
     * @return {@link ArrayList} of territories the player own
     */
    @Override
    public ArrayList<ITerritory> getTerritories() {
        return this.territories;
    }


    /**
     * fancy representation of the player status
     * @return fancy toString
     */
    @Override
    public String toString(){
        String delimiter = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(delimiter);
        sb.append(this.getStrategy().getName());
        sb.append(delimiter);
        sb.append("Territories:");
        sb.append(this.getTerritories().size());
        sb.append(delimiter);
        sb.append("Unused Armies:");
        sb.append(this.getUnusedArmies());
        sb.append(delimiter);
        sb.append("Used Armies:");
        sb.append(this.getUsedArmies());
        return sb.toString();
    }

    
    /**
     * @param count number of armies to be place into the territory
     * @param territory the territory he wants to place armies
     * @return if the action is done or not as {@link ActionResponse}
     */
    @Override
    public ActionResponse placeArmy(int count, ITerritory territory) {

        if(this.unusedArmies - count < 0)
            return new ActionResponse("No sufficient armies.");


        this.setUnusedArmies(this.unusedArmies - count);
        this.setUsedArmies(this.usedArmies + count);
        //place armies into the territory
        territory.placeArmies(count); 
        
        this.statusMessage = this.getName() + " placed " + Integer.toString(count)+" armies into " + territory.getName();
        sendNotify();
        Log.log(this.statusMessage);
        
        Log.log(this.getName() + " Unused armies = " + Integer.toString(this.getUnusedArmies()) +
                ", Used armies = " + Integer.toString(this.getUsedArmies()));
        
        return new ActionResponse(true, String.format("%d armies placed in %s", count, territory.getName()));
    }

    /**
     * another fancy representation of player status
     * @return table
     */
    @Override
    public String getState()
    {
    	StringBuilder sb = new StringBuilder();
        sb.append("\n====================");
        sb.append("\n");
        sb.append(this.getName());
        sb.append("\n");
        sb.append("--------------------");
        sb.append("\n");
        sb.append("Armies: ");
        sb.append("\n");
        sb.append("   Used: ");
        sb.append(this.getUsedArmies());
        sb.append("\n");
        sb.append("   Unused: ");
        sb.append(this.getUnusedArmies());
        sb.append("\n-------------------- ");
        sb.append("\n");
        sb.append("Territories: (:");
        sb.append("\n");
        for(ITerritory t: this.territories)
        {
            sb.append("   "+t.getName());
            sb.append(", ");
            sb.append("Armies : ");
            sb.append(t.getArmies());
            sb.append("\n");
        }
        sb.append("\n");

        sb.append("====================");
        return sb.toString();
    }

    /**
     * finds a territory by its name
     * @param territoryName is the name of the territory
     * @return the {@link Territory} object associated with the passed argument
     */
    @Override
    public ITerritory getTerritoryByName(String territoryName)
    {
        ITerritory result = null;
        for(ITerritory t:this.territories)
            if(t.getName().equalsIgnoreCase(territoryName))
                result = t;
        return result;

    }

    /**
     * @return randomly selected territory
     */
    @Override
    public ITerritory getRandomTerritory() {
        int max = this.getTerritories().size()-1;
        return this.getTerritories().get(util.Helpers.getRandomInt(max,0));
    }


    /**
     * move armies from a territory to another
     * @param from origin territory
     * @param to destination territory
     * @param number number of armies
     * @return if the operation is done or not
     */
    @Override
    public ActionResponse moveArmies(ITerritory from, ITerritory to, int number) {
        ActionResponse result = new ActionResponse();

        if(from.hasAdjacencyWith(to))
        {
            Log.log(this.getState());
            ActionResponse r = from.removeArmies(number);
            if (r.getOk())
            {
                to.placeArmies(number);
                this.statusMessage = String.format("%s moved %s armies from %s to %s.", this.getName(),
                        number, from.getName(),to.getName());
                
                sendNotify();
                Log.log(this.statusMessage);
                Log.log(this.getState());
            }
        }
        else
        {
        	 this.statusMessage = String.format(
                     "%s wanted to move %s armies from %s to %s, but there is no adjacencies.", this.getName()
                     , number, from.getName(), to.getName() );
             
             sendNotify();
            Log.log(LogMessageEnum.ERROT, this.statusMessage);
        }

        return result;
    }

    /**
     * find a territory to attack
     * @return territory to attack from and attack to
     */
    @Override
    public AttackPlan getTerritoryToAttack() {
        AttackPlan result = null;
        ITerritory t = this.getRandomTerritory();
        for(ITerritory a: t.getAdjacentTerritoryObjects())
        {
            if(a.getOwner() != this)
            {
                result = new AttackPlan(t,a);
                return result;
            }
        }
        return result;
    }

    /**
     * set the game manager for player
     * @param gm game manager
     */
    @Override
    public void setGameManager(GameManager gm) {
        this.gm = gm;
    }


    /**
     * set the game manager for player
     * @return used game manager
     */
    @Override
    public GameManager getGameManager() {
        return this.gm;
    }

    /**
     * Handles reinforcement phase by :
     * calculating the number of armies each player should get
     * let the given player decide where to place the given armies
     */
    public void reinforcement()
    {
        Log.log(String.format("============%s REINFORCEMENT STARTS===========", this.getName()));
        this.gm.setPhase("REINFORCEMENT PHASE");
        //Step 1: Reinforcement
        int newArmies = this.gm.calculateReinforcementArmies(this);
        sendNotification("PhaseChange:"+this.getName()+" Reinforcement, got "+newArmies+" armies");
        this.setUnusedArmies(newArmies);
        //Step 2: Place armies
        this.gm.placeArmies(this);
        
        Log.log(String.format("============%s REINFORCEMENT DONE===========", this.getName()));
    }


    /**
     * simple attack
     */
    public void attack()
    {
    	sendNotification("PhaseChange:"+this.getName()+" Attack");    	
    	// attack as per strategy
    	this.attack(strategy.getAttackAttempts());    	
    }

    /**
     * This will handle attack phase but not implemented yet
     */
    public void attack(int attempts)
    {
        Log.log(String.format("============%s ATTACK STARTS===========", this.getName()));
        Log.log(String.format("Strategy is %s", this.strategy.getName()));
        this.gm.setPhase("ATTACK PHASE");

        for(int a=1; a<= attempts; a++)
        {
            Log.log(String.format("Attack %s ", a));

            // Step 1: Design an attack plan
            AttackPlan ap = this.strategy.getAttackPlan(this);
            if (ap == null)
            {
            	this.statusMessage = "No territory found to attack.";
            	sendNotify();
                Log.log(LogMessageEnum.WARNING,"No territory found to attack.");
                break;
            }

            ITerritory attackFrom = ap.from;
            ITerritory attackTo = ap.to;
            
            //Notify the attack to View 
            sendNotification("GameChange: Attacked from "+attackFrom.getName()+" to "+attackTo.getName()+"("+attackTo.getOwner().getName()+")");
            
            // Step 2: Number of armies(Dices) for the battle
             int diceAttack = attackFrom.getOwner().getStrategy().diceToAttack(attackFrom.getOwner());

            //Step 3: Checking sufficient armies to attack
            if (attackFrom.getArmies() - diceAttack >= 1)
            {
                int diceDefend = attackTo.getOwner().getStrategy().diceToDefend(attackTo.getOwner());
                //Step 4: Checking sufficient armies to defend
                if(diceDefend == 2 && attackTo.getArmies() < 2)
                {
                    diceDefend = 1;
                }

                this.statusMessage = String.format("%s attacks %s from %s with %s armies and %s defends with %s armies", this.getName(), attackTo.getName(),
                        attackFrom.getName(), diceAttack, attackTo.getName(), diceDefend );
            	sendNotify();
                
                Logger.log(this.statusMessage);

                //Step 5: Rolling dices
                ArrayList<Integer> attackDices = new ArrayList<>();
                ArrayList<Integer> defendDices = new ArrayList<>();
                Dice dice = new Dice();
                for(int i=0; i<diceAttack; i++)
                    attackDices.add(dice.roll());
                for(int i=0; i<diceDefend; i++)
                    defendDices.add(dice.roll());

                //Step 6: Sorting die rolls
                Collections.sort(attackDices);
                Collections.sort(defendDices);

                this.statusMessage = String.format("%s(Attacker) rolled these dices %s",  attackFrom.getOwner().getName(),attackDices.toString());
            	sendNotify();
                Log.log(String.format("%s(Attacker) rolled these dices %s",  attackFrom.getOwner().getName(),attackDices.toString()));
                
                this.statusMessage = String.format("%s(Defender) rolled these dices %s",  attackTo.getOwner().getName(),defendDices.toString());
            	sendNotify();
                Log.log(String.format("%s(Defender) rolled these dices %s",  attackTo.getOwner().getName(),defendDices.toString()));

                //Step 7: calculating number of fights
                int fights = 0;
                if(attackDices.size() > defendDices.size())
                    fights = defendDices.size();
                else if(attackDices.size() < defendDices.size())
                    fights = attackDices.size();
                else
                    fights = attackDices.size();

                //Step 8: Deciding the battle
                for(int f=0; f<fights; f++)
                {
                    if(attackDices.get(0) > defendDices.get(0))
                    {
                        // Attacker wins
                        Log.log(attackTo.getOwner().getState());

                        this.statusMessage = String.format("1 of the armies in %s(Defender) was killed.", attackTo.getName());
                        sendNotify();
                        Logger.log(String.format("1 of the armies in %s(Defender) was killed.", attackTo.getName()));
                        attackTo.killArmies(1);
                        Log.log(attackTo.getOwner().getState());
                        
                        
                        // checking for occupying the territory
                        if(attackTo.getArmies()==0)
                        {
                        	this.statusMessage = String.format("%s occupies %s", attackFrom.getOwner().getName(),
                                    attackTo.getName());
                        	sendNotify();
                            Log.log(String.format("%s occupies %s", attackFrom.getOwner().getName(),
                                    attackTo.getName()));
                            Log.log(attackFrom.getOwner().getState());
                            attackTo.getOwner().lostTerritory(attackTo);
                            attackFrom.getOwner().ownTerritory(attackTo);
                            Card crd = this.getGameManager().cardDeck.grantCard(this);
                            this.statusMessage = String.format("%s gets one card %s, %s", this.getName(),
                                    crd.getCardName(), crd.getCardValue());
                        	sendNotify();
                            Log.log(String.format("%s gets one card %s, %s", this.getName(),
                                    crd.getCardName(), crd.getCardValue()));
                            Log.log(attackFrom.getOwner().getState());
                        }
                    }
                    else
                    {
                        // Defender wins
                        Log.log(attackFrom.getOwner().getState());
                        this.statusMessage = String.format("1 of the armies in %s(Attacker) was killed.",attackFrom.getName());
                        sendNotify();
                        Logger.log(String.format("1 of the armies in %s(Attacker) was killed.",attackFrom.getName()));
                        attackFrom.killArmies(1);
                        Log.log(attackFrom.getOwner().getState());
                    }

                    attackDices.remove(0);
                    defendDices.remove(0);
                }

                //Step 9: after attack if occupied move remaining attack armies to new territory
                if(attackTo.getOwner() == attackFrom.getOwner())
                {
                    //Step 10: calculating moving armies to new territory
                    int movingArmies = attackFrom.getOwner().getStrategy().getMovingArmiesToNewTerritory(this);
                    attackFrom.removeArmies(movingArmies);
                    attackTo.placeArmies(movingArmies);
                    this.statusMessage = String.format("%s places %s armies to occupied territory(%s)",
                            attackTo.getOwner().getName(), movingArmies, attackTo.getName());
                    sendNotify();
                    Log.log(String.format("%s places %s armies to occupied territory(%s)",
                            attackTo.getOwner().getName(), movingArmies, attackTo.getName()));
                    Logger.log(this.getState());
                }

            }
            else
            {
            	//Attack canceled
                this.statusMessage = String.format("Attacking %s from %s with %s armies canceled. %s -> %s", attackTo.getName(),
                        attackFrom.getName(), diceAttack, attackFrom.getArmies() , attackTo.getArmies());
                sendNotify();
            	Logger.log(String.format("Attacking %s from %s with %s armies canceled. %s -> %s", attackTo.getName(),
                        attackFrom.getName(), diceAttack, attackFrom.getArmies() , attackTo.getArmies()));
            }

            this.statusMessage = String.format("Attack %s finished.", a);
            sendNotify();
            Log.log(String.format("Attack %s finished.", a));
        }


        Log.log(String.format("============%s ATTACK DONE===========", this.getName()));
    }


    /**
     * does the fortification phase and randomly move armies to another territories
     * owned by the player
     */
    public void fortification()
    {
        Log.log(String.format("============%s FORTIFICATION STARTS===========", this.getName()));
        this.gm.setPhase("FORTIFICATION PHASE");
        sendNotification("PhaseChange:"+this.getName()+" Fortification");
        
        ITerritory from = this.strategy.getFortificationFromTerritory(this);
        int number = this.strategy.getFortificationArmies(this, from);
        ITerritory to = this.strategy.getFortificationToTerritory(this, from);


        this.moveArmies(from, to, number);


        Log.log(String.format("============%s FORTIFICATION DONE===========", this.getName()));
        sendNotification("Done his fortification");
    }

	/**
	 * @param string
	 */
	private void sendNotification(String string) {
		setChanged();
		notifyObservers(string);	
	}

	
	/**
     * implementation of Comparable
     * @param o player to compare to
     * @return if they are equal or not
     */
    @Override
    public int compareTo(IPlayer o) {
        return (int)(this.getDomination() - o.getDomination());
    }

    /**
     * What strategy is being used.
     * @return the strategy
     */
    @Override
    public IStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * set the strategy to play with
     * @param strategy new strategy
     */
    @Override
    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }


    /**
     * set the if player is active or not
     * @param status new status
     */
    @Override
    public void setStatus(boolean status) {
        this.isActive = status;
    }

    /**
     * get the if player is active or not
     * @return status
     */
    @Override
    public boolean getStatus() {
        return this.isActive;
    }

    /**
     * adds a new card to {@link #cards}
     * @param newCard is the card that this player got
     */
    @Override
    public void addCard(Card newCard) {
        this.cards.add(newCard);
    }

    /**
     * Returns {@link ArrayList} of cards owned by this player
     */
    @Override
    public ArrayList<Card> getCardSet() {
        ArrayList<Card> result  = new ArrayList<Card>();

        if(this.cards.size()>=3)
        {
            result = new ArrayList<>();
            for(int i=0; i<3; i++)
                result.add(this.cards.get(i));

            this.cards.removeAll(result);
        }

        return result;
    }

    
    /**
     * Returns {@link ArrayList} of cards owned by this player
     */
    public ArrayList<Card> getCards() {
    	return this.cards;
    }
   
        
    
    /**
     * return the number of cards this player has
     */
    @Override
    public int getCardsSize() {
        return this.cards.size();
    }

    
    /**
     * Notify the observers with his {@link #statusMessage} player status message
     */
    public void sendNotify(){
    		setChanged();
    		notifyObservers(this.statusMessage); 	
    }
    
    
    /**
     * Notify the observers with passed message
     * @param message is the message to be sent with notify
     */
    public void sendNotify(String message){    	
    		setChanged();
    		notifyObservers(message);
  
    }

    /**
     * finds the weakest territory
     * @return the weakest {@link Territory} among his territories
     */
    @Override
    public ITerritory getWeakestTerritory() {
        ITerritory result = null;

        result = this.territories.get(0);
        for(ITerritory t: this.territories)
        {
                if(t.getArmies() < result.getArmies() && t.getArmies()>1)
                result = t;
        }

        return result;
    }

    /**
     * returns the strongest territory
     * @return the strongest {@link Territory} among his territories
     */
    @Override
    public ITerritory getStrongestTerritory() {
        ITerritory result = null;

        result = this.territories.get(0);
        for(ITerritory t: this.territories)
        {
            if(t.getArmies() > result.getArmies())
                result = t;
        }

        return result;
    }


    /**
     * return how many trades the players has done so far
     * @return number of trades
     */
    @Override
    public int getTrades()
    {
        return this.trades;
    }


    /**
     * reduces the number of trades for card exchange
     */
    @Override
    public void increaseTrades()
    {
        this.trades++;
    }



}
