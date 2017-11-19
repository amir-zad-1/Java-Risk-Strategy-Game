package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;

public class Cheater implements IStrategy {
    @Override
    public int getAttackAttempts() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public ITerritory getInforcementTerritory(IPlayer p) {
        return null;
    }

    @Override
    public int getReinforcementArmies(IPlayer p) {
        return 0;
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
    public ITerritory getFortificationToTerritory(IPlayer p) {
        return null;
    }
}
