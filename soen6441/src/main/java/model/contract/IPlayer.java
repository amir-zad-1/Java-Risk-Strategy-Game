package model.contract;

import model.GameManager;
import util.ActionResponse;

import java.util.ArrayList;

public interface IPlayer {

    ActionResponse ownTerritory(ITerritory territory);
    ArrayList<ITerritory> getTerritories();


    String getName();
    void setName(String newName);

    void setUnusedArmies(int armies);
    int getUnusedArmies();

    void setUsedArmies(int armies);
    int getUsedArmies();

    String toString();

    ActionResponse placeArmy(int count, ITerritory territory);

    String getState();

    ITerritory getTerritoryByName(String territoryName);

    ITerritory getRandomTerritory();

    ActionResponse moveArmies(ITerritory from, ITerritory to, int number);

    void setGameManager(GameManager gm);
    GameManager getGameManager();

    void reinforcement();
    void attack();
    void fortification();


}
