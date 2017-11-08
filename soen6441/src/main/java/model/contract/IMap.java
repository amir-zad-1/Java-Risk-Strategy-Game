package model.contract;

import util.ActionResponse;

import java.util.ArrayList;

/**
 * Business functions for Map
 */
public interface IMap {

    ArrayList<IContinent> getContinents();
    void clearData();
    void fakeData();

}
