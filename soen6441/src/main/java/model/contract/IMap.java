package model.contract;

import util.ActionResponse;

import java.util.ArrayList;

public interface IMap {

    ArrayList<IContinent> getContinents();
    ActionResponse loadMap(String fullpath);
    ActionResponse saveMap(String fullpath);
    ActionResponse validateMap();

    ActionResponse removeTerritory(ITerritory t);
    ActionResponse addTerritory(ITerritory t);

}
