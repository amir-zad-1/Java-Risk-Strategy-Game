package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;
import util.Helpers;

import java.util.ArrayList;

public class Random implements IStrategy {

    @Override
    public int getAttackAttempts() {
        return util.Helpers.getRandomInt(5,1);
    }

    @Override
    public String getName() {
        return "Random";
    }

    @Override
    public ITerritory getInforcementTerritory(IPlayer p) {
        return p.getRandomTerritory();
    }

    @Override
    public int getReinforcementArmies(IPlayer p) {
        return util.Helpers.getRandomInt(p.getUnusedArmies(),1);
    }

    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {
        return p.getRandomTerritory();
    }

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

    @Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
        return Helpers.getRandomInt(from.getArmies(),1);
    }

    @Override
    public AttackPlan getAttackPlan(IPlayer p) {
        return p.getTerritoryToAttack();
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
}
