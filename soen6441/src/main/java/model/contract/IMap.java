package model.contract;

import util.ActionResponse;

import java.util.ArrayList;

public interface IMap {

    ArrayList<IContinent> getContinents();
    void clearData();
    void fakeData();

}
