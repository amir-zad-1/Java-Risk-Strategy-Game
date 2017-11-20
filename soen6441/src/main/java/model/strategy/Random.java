package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;
import util.Helpers;

import java.util.ArrayList;

/**
 * Random Strategy
 */
public class Random implements IStrategy {

    /**
     * how many attacks
     * @return number of attacks
     */
    @Override
    public int getAttackAttempts() {
        return util.Helpers.getRandomInt(5,1);
    }

    /**
     * name of the startegy
     * @return name
     */
    @Override
    public String getName() {
        return "Random";
    }

    /**
     * where to reinforce
     * @param p player
     * @return territory
     */
    @Override
    public ITerritory getInforcementTerritory(IPlayer p) {
        return p.getRandomTerritory();
    }

    /**
     * set number of reinforcement armies
     * @param p player
     * @return number of reinforcement
     */
    @Override
    public int getReinforcementArmies(IPlayer p) {
        return util.Helpers.getRandomInt(p.getUnusedArmies(),1);
    }

    /**
     * where to fortify
     * @param p player
     * @return territory
     */
    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {
        return p.getRandomTerritory();
    }

    /**
     * where to move fortification armies to
     * @param p player
     * @param from origin of fortification
     * @return destination of fortification
     */
    @Override
    public ITerritory getFortificationToTerritory(IPlayer p, ITerritory from) {
        ITerritory to;

        ArrayList<ITerritory> neighbours = from.getAdjacentNeighbours();
        if(neighbours.size()>0)
            to = neighbours.get(0);
        else
            to = p.getRandomTerritory();

        return to;
    }

    /**
     * number of fortification armes
     * @param p player
     * @param from origin of fortification
     * @return number of armies
     */@Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
        return Helpers.getRandomInt(from.getArmies(),1);
    }

    /**
     * Generates an attack plan for a player
     * @param p player
     * @return attack plan
     */
    @Override
    public AttackPlan getAttackPlan(IPlayer p) {
        return p.getTerritoryToAttack();
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
}
