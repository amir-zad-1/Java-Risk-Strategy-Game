package model.strategy;

import model.contract.IStrategy;

public class Defensive implements IStrategy {


    public Defensive()
    {

    }

    @Override
    public int getAttackAttempts() {
        return 0;
    }

    @Override
    public String getName() {
        return "Defensive";
    }
}
