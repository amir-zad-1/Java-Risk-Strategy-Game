package model.strategy;

import model.contract.IStrategy;

public class Normal implements IStrategy {

    public Normal()
    {

    }

    @Override
    public int getAttackAttempts() {
        return 1;
    }

    @Override
    public String getName() {
        return "Normal";
    }
}
