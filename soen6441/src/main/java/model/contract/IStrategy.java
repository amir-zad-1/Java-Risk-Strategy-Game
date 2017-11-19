package model.contract;

import model.AttackPlan;

public interface IStrategy {

    int getAttackAttempts();
    String getName();


    ITerritory getInforcementTerritory(IPlayer p);
    int getReinforcementArmies(IPlayer p);

    ITerritory getFortificationFromTerritory(IPlayer p);
    ITerritory getFortificationToTerritory(IPlayer p);

    AttackPlan getAttackPlan(IPlayer p);
    int diceToAttack(IPlayer p);
    int diceToDefend(IPlayer p);
    int getMovingArmiesToNewTerritory(IPlayer p);

}
