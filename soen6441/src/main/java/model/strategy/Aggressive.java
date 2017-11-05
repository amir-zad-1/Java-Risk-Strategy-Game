package model.strategy;

import model.contract.IStrategy;

public class Aggressive implements IStrategy {

    public Aggressive()
    {

    }

    @Override
    public int getAttackAttempts() {
        return 6;
    }

    @Override
    public String getName() {
        return "Aggressive";
    }
}
