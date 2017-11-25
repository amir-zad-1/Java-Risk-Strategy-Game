package model;

import model.contract.IContinent;
import model.contract.IMap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * represents Map in the game
 */
public class Map implements IMap,Serializable {

	/**
	 * {@link #serialVersionUID} used during deserialization to verify that the sender and receiver 
	 * of a serialized object have loaded classes for that object that are compatible with respect to 
	 * serialization. If the receiver has loaded a class for the object that has a different {@link #serialVersionUID}
	 */
	private static final long serialVersionUID = 5631589509991237355L;
	
    private ArrayList<IContinent> continents = new ArrayList<>();
    private String name = "Map ";

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


    /**
     * loads data
     */
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

    /**
     * sets name
     * @param name name value
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get name
     * @return name value
     */
    @Override
    public String getName() {
        return this.name;
    }
}