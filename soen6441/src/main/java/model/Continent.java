package model;

import model.contract.IContinent;
import model.contract.IPlayer;
import model.contract.ITerritory;

import java.util.ArrayList;

public class Continent implements IContinent {

    private String name;
    private ArrayList<ITerritory> territories;
    private int value = 0;

    public Continent(String name){
        this.name = name;
        this.territories = new ArrayList<>();
    }

    public void setName(String name){ this.name=name; }
    public String getName() {return this.name;}

    @Override
    public int getContinentValue() {
        return this.value;
    }

    @Override
    public void setContinentValue(int value) {
        this.value = value;
    }

    @Override
    public boolean controlByPlayer(IPlayer p) {
        boolean result = true;
        for(ITerritory t:this.getTerritories())
        {
            if(!t.getOwner().equals(p))
            {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public ArrayList<ITerritory> getTerritories() {
         return this.territories;
    }

    @Override
    public void addTerritory(ITerritory t) {
        this.territories.add(t);
    }

    @Override
    public void removeTerritory(ITerritory t)
    {
        int index = this.territories.indexOf(t);
        this.territories.remove(index);
    }
}
