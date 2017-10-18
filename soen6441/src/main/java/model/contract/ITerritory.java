package model.contract;

public interface ITerritory {

    void setOwner(IPlayer player);
    IPlayer getOwner();
    String getName();

    int getArmies();
    void placeArmies(int count);

}
