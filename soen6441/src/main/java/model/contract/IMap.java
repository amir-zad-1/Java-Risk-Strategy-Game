package model.contract;

import java.util.ArrayList;

public interface IMap {

    ArrayList<IContinent> getContinents();
    void loadMap(String fullpath);

}
