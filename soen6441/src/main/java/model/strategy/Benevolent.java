package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;
import util.Helpers;

public class Benevolent implements IStrategy {
    @Override
    public int getAttackAttempts() {
        return 0;
    }

    @Override
    public String getName() {
        return "Benevolent";
    }

    @Override
    public ITerritory getInforcementTerritory(IPlayer p) {

        return p.getWeakestTerritory();
    }

    @Override
    public int getReinforcementArmies(IPlayer p) {
        return p.getUnusedArmies();
    }

    @Override
    public AttackPlan getAttackPlan(IPlayer p) {
        return null;
    }

    @Override
    public int diceToAttack(IPlayer p) {
        return 0;
    }

    @Override
    public int diceToDefend(IPlayer p) {
        return Helpers.getRandomInt(2,1);
    }

    @Override
    public int getMovingArmiesToNewTerritory(IPlayer p) {
        return Helpers.getRandomInt(2,1);
    }

    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {
        return p.getStrongestTerritory();
    }

    @Override
    public ITerritory getFortificationToTerritory(IPlayer p, ITerritory from) {
        return p.getWeakestTerritory();
    }

    @Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
        return Helpers.getRandomInt(2,1);
    }
}
