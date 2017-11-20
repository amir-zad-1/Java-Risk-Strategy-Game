package model;

import model.contract.IContinent;
import model.contract.IMap;

import java.util.ArrayList;

/**
 * represents Map in the game
 */
public class Map implements IMap {


    private ArrayList<IContinent> continents = new ArrayList<>();
    private String name = "Untitled";

    public static int totalnumberOfTerritories = 0;
    
    /**
     * Constructor
     */
    public Map()
    {
        this.loadData();
    }


    /**
     *
     * @return strategies of continents in the map
     */
    @Override
    public ArrayList<IContinent> getContinents() {
        return this.continents;
    }


    private void loadData(){
    	this.continents = new ArrayList<>();
    	totalnumberOfTerritories = 0;
    	for(String continent: MapDataBase.continents.keySet()){
    		IContinent c = new Continent(continent);
    		for(Territory territory: MapDataBase.continents.get(continent).values()){
    			c.addTerritory(territory);
    			totalnumberOfTerritories++;
    		}
    		this.continents.add(c);	
    	}
    }

    /**
     * clears the continents from the map
     */
    public void clearData()
    {
        this.continents = new ArrayList<>();
    }

    /**
     * fills the map with the fake data
     */
    public void fakeData()
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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}