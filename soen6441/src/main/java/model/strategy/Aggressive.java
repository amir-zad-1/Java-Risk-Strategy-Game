package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;
import util.Helpers;

public class Aggressive implements IStrategy {
    @Override
    public int getAttackAttempts() {
        return 5;
    }

    @Override
    public String getName() {
        return "Aggressive";
    }

    @Override
    public ITerritory getInforcementTerritory(IPlayer p) {
        return p.getStrongestTerritory();
    }

    @Override
    public int getReinforcementArmies(IPlayer p) {
        return p.getUnusedArmies();
    }

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

    @Override
    public int diceToAttack(IPlayer p) {
        return Helpers.getRandomInt(3,1);
    }

    @Override
    public int diceToDefend(IPlayer p) {
        return Helpers.getRandomInt(2,1);
    }

    @Override
    public int getMovingArmiesToNewTerritory(IPlayer p) {
        return p.getWeakestTerritory().getArmies()-1;
    }

    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {
        return p.getWeakestTerritory();
    }

    @Override
    public ITerritory getFortificationToTerritory(IPlayer p, ITerritory from) {
        return p.getStrongestTerritory();
    }

    @Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
        return Helpers.getRandomInt(from.getArmies(),1);
    }
}
