package model.contract;

import util.ActionResponse;

public interface ITerritory {

    void setOwner(IPlayer player);
    IPlayer getOwner();
    String getName();

    int getArmies();
    void placeArmies(int count);
    ActionResponse removeArmies(int count);
    boolean hasAdjacencyWith(ITerritory t);

}
