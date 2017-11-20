package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;

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
        return null;
    }

    @Override
    public int diceToAttack(IPlayer p) {
        return 0;
    }

    @Override
    public int diceToDefend(IPlayer p) {
        return 0;
    }

    @Override
    public int getMovingArmiesToNewTerritory(IPlayer p) {
        return 0;
    }

    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {
        return null;
    }

    @Override
    public ITerritory getFortificationToTerritory(IPlayer p, ITerritory from) {
        return null;
    }

    @Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
        return 0;
    }
}
