package model;

import model.contract.IContinent;
import model.contract.IPlayer;
import model.contract.ITerritory;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Continent class that holds all territories
 * 
 */
public class Continent  implements IContinent,Serializable {

	/**
	 * {@link #serialVersionUID} used during deserialization to verify that the sender and receiver 
	 * of a serialized object have loaded classes for that object that are compatible with respect to 
	 * serialization. If the receiver has loaded a class for the object that has a different {@link #serialVersionUID}
	 */
	private static final long serialVersionUID = 4893561606187077709L;

	/**
     * {@link #name} is the Continent name
     */
    private String name;
    
    /**
     * {@link #territories} are the list of territories in a Continent
     */
    private ArrayList<ITerritory> territories;
    
    /**
     * {@link #value} is the value of the continent
     */
    private int value = 0;

    /**
     * Constructor of the class that sets name of the continent
     * @param name name of the continent
     */
    public Continent(String name){
        this.name = name;
        this.territories = new ArrayList<>();
    }

    /**
     * sets name of the continent
     * @param name name
     */
    public void setName(String name){ this.name=name; }

    /**
     * returns name of the continent
     * @return name
     */
    public String getName() {return this.name;}

    /**
     * returns value of the continent
     * @return value of the continent
     */
    @Override
    public int getContinentValue() {
        return this.value;
    }

    /**
     * sets value of the continent
     * @param value name of the continent
     */
    @Override
    public void setContinentValue(int value) {
        this.value = value;
    }

    
    /**
     * @param p is the player who want to check check if he owns entire continent
     * @return true if a players owns entire continent
     */
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


    
    /**
     * @return {@link ArrayList} of {@link Territory} belongs to this continent
     */
    @Override
    public ArrayList<ITerritory> getTerritories() {
         return this.territories;
    }

    /**
     * This method adds a territory to this continent object
     * @param territory is the territory want to add into the continent object
     */
    @Override
    public void addTerritory(ITerritory territory) {
        this.territories.add(territory);
    }
    
    /**
     * This method is to remove a territory from a continent
     * @param territory is the territory want to be removed from continent object
     */
    @Override
    public void removeTerritory(ITerritory territory)
    {
        int index = this.territories.indexOf(territory);
        this.territories.remove(index);
    }
}
