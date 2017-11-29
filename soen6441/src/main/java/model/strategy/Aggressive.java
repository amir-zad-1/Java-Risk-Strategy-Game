package model.strategy;

import java.io.Serializable;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;
import util.Helpers;

/**
 * Aggressive Strategy
 */
public class Aggressive implements IStrategy,Serializable {
    
	/**
	 * Deserialization uses this number to ensure that a loaded class corresponds 
	 * exactly to a serialized object while saving rhe game state 
	 */
	private static final long serialVersionUID = 3617596243082091692L;


	/**
     * how many attacks
     * @return number of attacks
     */
    @Override
    public int getAttackAttempts() {
        return 5;
    }

    /**
     * name of the startegy
     * @return name
     */
    @Override
    public String getName() {
        return "Aggressive";
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
        return p.getUnusedArmies();
    }

    /**
     * Generates an attack plan for a player
     * @param p player
     * @return attack plan
     */
    @Override
    public AttackPlan getAttackPlan(IPlayer p) {

        AttackPlan plan = null;
        ITerritory from = p.getStrongestTerritory();

        try
        {
            ITerritory to = from.getAdjacentTerritoryObjects().get(0);
            for(ITerritory t : from.getAdjacentTerritoryObjects())
            {
                if (t.getArmies() < to.getArmies())
                    to = t;
            }

            plan = new AttackPlan(from, to);
        }
        catch (Exception e)
        {
            plan = null;
        }

        return plan;
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

        //return p.getWeakestTerritory().getArmies()-1;
        int result = p.getWeakestTerritory().getArmies()-1;
        if (result<0)
            result = 0;
        return result;

    }

    /**
     * where to fortify
     * @param p player
     * @return territory
     */
    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {
        return p.getWeakestTerritory();
    }

    /**
     * where to move fortification armies to
     * @param p player
     * @param from origin of fortification
     * @return destination of fortification
     */
    @Override
    public ITerritory getFortificationToTerritory(IPlayer p, ITerritory from) {
        return p.getStrongestTerritory();
    }


    /**
     * number of fortification armes
     * @param p player
     * @param from origin of fortification
     * @return number of armies
     */
    @Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
    	System.out.println("Agressive "+from.getArmies()+" "+1);
        return Helpers.getRandomInt(from.getArmies(),1);
    }
}
