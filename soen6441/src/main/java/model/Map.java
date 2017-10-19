package model;

import model.contract.IContinent;
import model.contract.IMap;
import model.contract.ITerritory;
import util.ActionResponse;

import java.util.ArrayList;

public class Map implements IMap {


    private ArrayList<IContinent> continents = new ArrayList<>();

    public Map()
    {
        this.loadData();
    }


    @Override
    public ArrayList<IContinent> getContinents() {
        return this.continents;
    }

    @Override
    public ActionResponse loadMap(String fullpath) {
        //todo: loadMap
    	return null;
    }

    @Override
    public ActionResponse saveMap(String fullpath) {
        return null;
    }

    @Override
    public ActionResponse validateMap() {
        return null;
    }

    @Override
    public ActionResponse removeTerritory(ITerritory t) {
        return null;
    }

    @Override
    public ActionResponse addTerritory(ITerritory t) {
        return null;
    }

    private void loadData(){
    	this.continents = new ArrayList<>();
    	for(String continent: MapDataBase.continents.keySet()){
    		IContinent c = new Continent(continent);
    		for(Territory territory: MapDataBase.continents.get(continent).values()){    			
    			c.addTerritory(new Territory(territory.getTerritoryName(),territory.getContinentName()));	
    		}
    		this.continents.add(c);	
    	}
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
