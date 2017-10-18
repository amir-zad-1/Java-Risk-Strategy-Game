package model.contract;

import model.Player;

import java.util.ArrayList;

public interface IGameManager {

    void allocateTerritories(ArrayList<Player> players);

    /**
     * accrording to the game rules, number of armies each player has is set by the number of players
     * @param players number of players
     */
    void allocateArmies(ArrayList<Player> players);

}
