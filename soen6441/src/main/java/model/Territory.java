package model;

import model.contract.IPlayer;
import model.contract.ITerritory;
import util.Logger;

import java.util.ArrayList;

public class Territory implements ITerritory {
    private String continentName;
	private String territoryName;
	private String coordinates;
    private ArrayList<String> adjacentTerritories = new ArrayList<String>();    
    private int numberOfArmies;
    private String currentPlayer;
	
    public Territory(String n_continentName, String n_territoryName,String n_coordinates, ArrayList<String> n_adjacentTerritories) {
		this.continentName = n_continentName;
		this.territoryName = n_territoryName;
		this.coordinates = n_coordinates;
		this.adjacentTerritories = n_adjacentTerritories;
	}

	/**
	 * @return the continentName
	 */
	public String getContinentName() {
		return continentName;
	}

	/**
	 * @param continentName the continentName to set
	 */
	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	/**
	 * @return the territoryName
	 */
	public String getTerritoryName() {
		return territoryName;
	}

	/**
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
	 * @return the numberOfArmies
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


    @Override
    public void setOwner(IPlayer player) {
        this.owner = player;
        Logger.log(this.territoryName + " is owned by " + player.getName());
    }

    @Override
    public IPlayer getOwner() {
        return this.owner;
    }

    //todo: added by Amir starts
    private IPlayer owner;
    public Territory(String name, String continentName){ this.territoryName=name; this.continentName=continentName; }
    public String getName(){ return this.territoryName; }

    @Override
    public int getArmies() {
        return this.numberOfArmies;
    }

    @Override
    public void placeArmies(int count) {
        this.numberOfArmies += count;
    }
    //todo: added by Amir ends
}
