package model.contract;

import model.AttackPlan;
import model.Card;
import model.GameManager;
import util.ActionResponse;
import util.Color;

import java.util.ArrayList;

/**
 * Enforces every Player object to has below functionality
 * @author SA
 */
public interface IPlayer extends Comparable<IPlayer> {

    ActionResponse ownTerritory(ITerritory territory);
    ActionResponse lostTerritory(ITerritory territory);
    ArrayList<ITerritory> getTerritories();


    String getName();
    void setName(String newName);
    double getDomination();
    void setDomination(double value);

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

    AttackPlan getTerritoryToAttack();

    void setGameManager(GameManager gm);
    GameManager getGameManager();

    void reinforcement();
    void attack(int attempts);
    void attack();
    void fortification();

    int compareTo(IPlayer o);


    IStrategy getStrategy();
    void setStrategy(IStrategy strategy);
    void setStatus(boolean status);
    boolean getStatus();

    void addCard(Card crd);
    ArrayList<Card> getCardSet();
    int getTrades();
    void increaseTrades();
    int getCardsSize();
	
    void setColor(Color randomColor);

	void sendNotify(String status);

	ITerritory getWeakestTerritory();
	ITerritory getStrongestTerritory();
}
