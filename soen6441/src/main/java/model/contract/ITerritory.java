package model.contract;

import util.ActionResponse;

import java.util.ArrayList;


/**
 * Business functions for Territory
 */
public interface ITerritory {

    void setOwner(IPlayer player);
    IPlayer getOwner();
    String getName();

    int getArmies();
    void placeArmies(int count);
    ActionResponse removeArmies(int count);
    ActionResponse killArmies(int count);
    boolean hasAdjacencyWith(ITerritory t);
    ArrayList<ITerritory> getAdjacentTerritoryObjects();
    ArrayList<ITerritory> getAdjacentNeighbours();

}
