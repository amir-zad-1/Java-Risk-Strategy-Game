package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;
import util.Helpers;

import java.util.ArrayList;
import java.util.Iterator;

public class Cheater implements IStrategy {
    @Override
    public int getAttackAttempts() {
        return 1;
    }

    @Override
    public String getName() {
        return "Cheater";
    }

    @Override
    public ITerritory getInforcementTerritory(IPlayer p) {
        return p.getStrongestTerritory();
    }

    @Override
    public int getReinforcementArmies(IPlayer p) {
        for(ITerritory t : p.getTerritories())
        {
            t.placeArmies( t.getArmies() * 2 );
        }
        return util.Helpers.getRandomInt(p.getUnusedArmies(),1);
    }

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
        return 1;
    }

    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {
        return p.getStrongestTerritory();
    }

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

    @Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
        return 1;
    }
}
