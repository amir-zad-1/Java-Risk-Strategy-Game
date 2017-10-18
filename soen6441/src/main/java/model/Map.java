package model;

import model.contract.IContinent;
import model.contract.IMap;

import java.util.ArrayList;

public class Map implements IMap {

    private String mapFile;
    private ArrayList<IContinent> continents = new ArrayList<>();

    public Map(String mapfile)
    {
        this.mapFile = mapfile;
        this.fakeData();
    }


    @Override
    public ArrayList<IContinent> getContinents() {
        return this.continents;
    }

    private void fakeData()
    {
        this.continents = new ArrayList<>();


        IContinent c1 = new Continent("Asia");
        this.continents.add(c1);
        c1.addTerritory(new Territory("Iran",c1.getName()));
        c1.addTerritory(new Territory("India",c1.getName()));
        c1.addTerritory(new Territory("Mexico",c1.getName()));
        c1.addTerritory(new Territory("Russia",c1.getName()));

        IContinent c2 = new Continent("Africa");
        this.continents.add(c2);
        c2.addTerritory(new Territory("Egypt",c2.getName()));
        c2.addTerritory(new Territory("Kenya",c2.getName()));

        IContinent c3 = new Continent("America");
        this.continents.add(c3);
        c3.addTerritory(new Territory("China", c3.getName()));
        c3.addTerritory(new Territory("Canada", c3.getName()));
        c3.addTerritory(new Territory("Argentina", c3.getName()));

    }
}
