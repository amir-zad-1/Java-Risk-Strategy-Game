package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;
import util.Helpers;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * cheater strategy
 */
public class Cheater implements IStrategy,Serializable{
    
	/**
	 * Deserialization uses this number to ensure that a loaded class corresponds 
	 * exactly to a serialized object while saving rhe game state 
	 */
	private static final long serialVersionUID = 8524490775170198242L;

	/**
     * how many attacks
     * @return number of attacks
     */
    @Override
    public int getAttackAttempts() {
        return 1;
    }

    /**
     * name of the startegy
     * @return name
     */
    @Override
    public String getName() {
        return "Cheater";
    }

    /**
     * where to reinforce
     * @param p player
     * @return territory
     */
    @Override
    public ITerritory getInforcementTerritory(IPlayer p) {
        return p.getStrongestTerritory();
    }

    /**
     * set number of reinforcement armies
     * @param p player
     * @return number of reinforcement
     */
    @Override
    public int getReinforcementArmies(IPlayer p) {
        for(ITerritory t : p.getTerritories())
        {
            t.placeArmies( t.getArmies() * 2 );
        }
        return util.Helpers.getRandomInt(p.getUnusedArmies(),1);
    }

    /**
     * Generates an attack plan for a player
     * @param p player
     * @return attack plan
     */
    @Override
    public AttackPlan getAttackPlan(IPlayer p) {

        ArrayList<ITerritory> newTerritories = new ArrayList<>();
        for(ITerritory t : p.getTerritories())
        {
            for(ITerritory n : t.getAdjacentTerritoryObjects())
            {
                if (n.getOwner() != p)
                {
                    newTerritories.add(n);
                }
            }
        }

        for(ITerritory t : newTerritories) {
            t.getOwner().lostTerritory(t);
            p.ownTerritory(t);
        }
        return null;
    }

    /**
     * role dice for attacking
     * @param p player
     * @return int dice
     */
    @Override
    public int diceToAttack(IPlayer p) {
        return Helpers.getRandomInt(3,1);
    }

    /**
     * role dice to defend
     * @param p player
     * @return dice
     */
    @Override
    public int diceToDefend(IPlayer p) {
        return Helpers.getRandomInt(2,1);
    }

    /**
     * how many armies should move to new territory
     * @param p player
     * @return number of armies
     */
    @Override
    public int getMovingArmiesToNewTerritory(IPlayer p) {
        return 1;
    }

    /**
     * where to fortify
     * @param p player
     * @return territory
     */
    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {
        return p.getStrongestTerritory();
    }

    /**
     * where to move fortification armies to
     * @param p player
     * @param from origin of fortification
     * @return destination of fortification
     */
    @Override
    public ITerritory getFortificationToTerritory(IPlayer p, ITerritory from) {
        for(ITerritory t : p.getTerritories())
        {
            for(ITerritory n : t.getAdjacentTerritoryObjects())
            {
                if (n.getOwner() != p)
                {
                    p.placeArmy(t.getArmies() * 2, t);
                }
            }
        }
        return p.getWeakestTerritory();
    }

    /**
     * number of fortification armes
     * @param p player
     * @param from origin of fortification
     * @return number of armies
     */
    @Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
        return 1;
    }
}
