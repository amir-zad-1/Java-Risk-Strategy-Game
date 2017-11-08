package model.contract;

import java.util.ArrayList;

/**
 * Enforces all the Continents should has below functionality
 * @author SA
 */
public interface IContinent {

    ArrayList<ITerritory> getTerritories();
    void addTerritory(ITerritory t);
    void removeTerritory(ITerritory t);
    String getName();
    int getContinentValue();
    void setContinentValue(int value);
    boolean controlByPlayer(IPlayer p);
}
