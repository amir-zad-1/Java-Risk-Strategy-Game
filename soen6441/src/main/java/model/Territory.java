package model;

import model.contract.IContinent;
import model.contract.IPlayer;
import model.contract.ITerritory;
import util.ActionResponse;
import util.LogMessageEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Territory POJO class 
*/
public class Territory  implements ITerritory,Serializable {
	
	/**
	 * {@link #serialVersionUID} used during deserialization to verify that the sender and receiver 
	 * of a serialized object have loaded classes for that object that are compatible with respect to 
	 * serialization. If the receiver has loaded a class for the object that has a different {@link #serialVersionUID}
	 */
	private static final long serialVersionUID = -8134828229182086176L;
	
	private String continentName;
	private String territoryName;
	private String coordinates;
    private ArrayList<String> adjacentTerritories = new ArrayList<String>();    
    private int numberOfArmies;
    private String currentPlayer;
    private IContinent continent;
	
    /**
     * Initializes the Territory Object with below parameters
     * @param n_continentName is the continent name
     * @param n_territoryName is the territory name
     * @param n_coordinates is the coordinates
     * @param n_adjacentTerritories are the adjacent territories
     */
    public Territory(String n_continentName, String n_territoryName,String n_coordinates, ArrayList<String> n_adjacentTerritories) {
    	this.continentName = n_continentName;
		this.territoryName = n_territoryName;
		this.coordinates = n_coordinates;
		this.adjacentTerritories = n_adjacentTerritories;

	}

	/**
	 * returns name of the continent
	 * @return the continentName
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * set name of the continent
	 * @param continentName the continentName to set
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * returns name of the territory
	 * @return the territoryName
	 */
	public String getTerritoryName() {
		return territoryName;
	}

	/**
     * sets name of the territory
	 * @param territoryName the territoryName to set
	 */
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	/**
	 * @return the adjacentTerritories
	 */
	public ArrayList<String> getAdjacentTerritories() {
		return adjacentTerritories;
	}

	/**
	 * @param adjacentTerritories the adjacentTerritories to set
	 */
	public void setAdjacentTerritories(ArrayList<String> adjacentTerritories) {
		this.adjacentTerritories = adjacentTerritories;
	}

	/**
	 * @return the numberOfArmies territory has
	 */
	public int getNumberOfArmies() {
		return numberOfArmies;
	}

	/**
	 * @param numberOfArmies the numberOfArmies to set
	 */
	public void setNumberOfArmies(int numberOfArmies) {
		this.numberOfArmies = numberOfArmies;
	}

	/**
	 * @return the currentPlayer
	 */
	public String getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * @return the coordinates
	 */
	public String getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}


    /**
     * sets the owner of the territory
     * @param player new owner
     */
	@Override
    public void setOwner(IPlayer player) {
        this.owner = player;
        Log.log(this.territoryName + " is owned by " + player.getName());
    }

    /**
     * sets owner of the territory
     * @return owner
     */
    @Override
    public IPlayer getOwner() {
        return this.owner;
    }

    //todo: added by Amir starts
    private IPlayer owner;

    /**
     * Constructor
     * @param name name of territory
     * @param continentName continent name
     */
    public Territory(String name, String continentName)
    {
        this.territoryName=name;
        this.continentName=continentName;
    }

   

	/**
     * @return name of the territory
     */
    public String getName()
    {
        return this.territoryName;
    }

	/**
	 * gets continents object to which this territory belongs
	 * @return continent
	 */
    @Override
	public IContinent getContinent() {
		return this.continent;
	}

	/**
	 * sets continent
	 * @param c continent value
	 */
	@Override
	public void setContinent(IContinent c) {
		this.continent = c;
	}


	/**
     * @return number of armies in the territory
     */
    @Override
    public int getArmies() {
        return this.numberOfArmies;
    }

    /**
     * adds given number of armies to the current armies
     * @param count number of armies to add
     */
    @Override
    public void placeArmies(int count) {
    	this.numberOfArmies += count;	
    }


    /**
     * Checks if there are at least one army in the territory then
     * removes armies from the territory
     * @param count number or armies to remove
     * @return remove process result
     */
    @Override
	public ActionResponse removeArmies(int count) {
		ActionResponse res = new ActionResponse();

		if ((this.getArmies() - count) >= 1)
		{
			this.setNumberOfArmies(this.getArmies() - count);
			res.setOk(true);
			Log.log(String.format("%s armies left %s", count, this.getName()));
		}
		else
		{
			Log.log(LogMessageEnum.ERROT, String.format("%s!, At least 1 army should be in %s", count, this.getName()));
			res.setOk(false);
		}

		return res;
	}

	/** 
	 * Call this method to kill the armies in this territory
	 * @param count is number of armies to be killed
	 */
	@Override
	public ActionResponse killArmies(int count) {
		ActionResponse res = new ActionResponse();
		this.setNumberOfArmies(this.getArmies() - count);
		res.setOk(true);
		Log.log(String.format("%s armies get killed in %s", count, this.getName()));

		return res;
	}

	/**
     * checks if this territory has Adjacency with the given territory
     * @param t second territory to check
     * @return if there is or not
     */
	@Override
	public boolean hasAdjacencyWith(ITerritory t) {
		if(this.adjacentTerritories.contains(t.getName())){
			return true;
		}
		return false;		
	}
	
	
	/**
	 * @return all adjacent territories Objects for this territory
	 */
	public ArrayList<ITerritory> getAdjacentTerritoryObjects(){
		ArrayList<ITerritory> adjacentTerritoriesObjects = new ArrayList<ITerritory>();
		for(HashMap<String,Territory> territories : MapDataBase.continents.values()){
			for(Territory territory:territories.values()){
				if(this.adjacentTerritories.contains(territory.getTerritoryName())){
					adjacentTerritoriesObjects.add(territory);
				}
			}
		}
		return adjacentTerritoriesObjects;		
	}

	/**
	 * @return {@link ArrayList} of adjacent {@link Territory}'s a player has own
	 */
	@Override
	public ArrayList<ITerritory> getAdjacentNeighbours() {
		ArrayList<ITerritory> result = new ArrayList<>();

		for(ITerritory t: this.getAdjacentTerritoryObjects())
		{
			if (t.getOwner() == this.getOwner())
				result.add(t);
		}

		return result;
	}

}
