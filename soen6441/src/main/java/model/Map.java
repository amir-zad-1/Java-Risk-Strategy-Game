package model;

import model.contract.IContinent;
import model.contract.IMap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * represents the Map in the game in the form of {@link ArrayList}
 */
public class Map implements IMap,Serializable {

	/**
	 * {@link #serialVersionUID} used during deserialization to verify that the sender and receiver 
	 * of a serialized object have loaded classes for that object that are compatible with respect to 
	 * serialization. If the receiver has loaded a class for the object that has a different {@link #serialVersionUID}
	 */
	private static final long serialVersionUID = 5631589509991237355L;
	
    /** contains {@link ArrayList} of {@link Continent}'s */
    private ArrayList<IContinent> continents = new ArrayList<>();
    
    /** contains  name of the map */
    private String name = "Map";

    /**
     * holds total number of territories in the map
     */
    private int totalnumberOfTerritories = 0;
    
    
    /**
     * Constructor which initializes the data into {@link #continents} 
     */
    public Map()
    {
        this.loadData();
    }


    /**
     *
     * @return {@link ArrayList} of continents on the map
     */
    @Override
    public ArrayList<IContinent> getContinents() {
        return this.continents;
    }


    /**
     * loads data which converts the HashMap to {@link #continents}
     */
    private void loadData(){
    	this.continents = new ArrayList<>();
    	setTotalnumberOfTerritories(0);
    	for(String continent: MapDataBase.continents.keySet()){
    		IContinent c = new Continent(continent);
    		for(Territory territory: MapDataBase.continents.get(continent).values()){
    			c.addTerritory(territory);
    			this.totalnumberOfTerritories++;
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
     * sets name of the map {@link #name}
     * @param name of the map
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get name of the map
     * @return {@link #name} value
     */
    @Override
    public String getName() {
        return this.name;
    }


	/**
	 * @return the totalnumberOfTerritories
	 */
	public int getTotalnumberOfTerritories() {
		return totalnumberOfTerritories;
	}


	/**
	 * @param totalnumberOfTerritories the {@link #totalnumberOfTerritories} to set
	 */
	public void setTotalnumberOfTerritories(int totalnumberOfTerritories) {
		this.totalnumberOfTerritories = totalnumberOfTerritories;
	}
}