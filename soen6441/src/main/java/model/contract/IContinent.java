package model.contract;

import java.util.ArrayList;

public interface IContinent {

    ArrayList<ITerritory> getTerritories();
    void addTerritory(ITerritory t);
    void removeTerritory(ITerritory t);
    String getName();
    int getContinentValue();
    void setContinentValue(int value);
    boolean controlByPlayer(IPlayer p);
}
