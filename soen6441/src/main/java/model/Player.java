package model;


import com.sun.javafx.binding.StringFormatter;

import controller.LoggerController;
import model.contract.ITerritory;
import model.contract.IPlayer;
import util.ActionResponse;
import util.Color;
import util.Helpers;
import util.LogMessageEnum;
import util.expetion.NoSufficientArmiesExption;

import java.util.ArrayList;

/**
 * This is player class
 *
 * @author Amir
 * @version 0.1.0
 */
public class Player implements IPlayer {


    private String name;
    private Color color;
    private int unusedArmies = 0;
    private int usedArmies = 0;
    private ArrayList<ITerritory> territories;
    private GameManager gm;

    /**
     * Constructor
     * @param name  player name
     * @param color player color
     */
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
    }

    /**
     *
     * @return player name
     */
    @Override
    public String getName(){
        return this.name;
    }

    /**
     *
     * @param newName new name for the  player
     */
    @Override
    public void setName(String newName){
        this.name = newName;
    }


    /**
     * set number of unused arimes for player
     * @param armies number of new armies
     */
    @Override
    public void setUnusedArmies(int armies) { this.unusedArmies = armies; }

    /**
     * set number of unused arimes for player
     */
    @Override
    public int getUnusedArmies(){ return this.unusedArmies; }

    /**
     *
     * @param armies number or unused armies to be set
     */
    @Override
    public void setUsedArmies(int armies) { this.usedArmies = armies; }

    /**
     * sets number of new armies
     * @return new armies
     */
    @Override
    public int getUsedArmies(){ return this.usedArmies; }

    /**
     *
     * @param color new color
     */
    public void setColor(Color color){ this.color = color; }

    /**
     *
     * @return player's color
     */
    public Color getColor() { return this.color; }

    /**
     *
     * @param territory territory to be owned
     * @return if the operation was successful or not
     */
    @Override
    public ActionResponse ownTerritory(ITerritory territory) {
        territory.setOwner(this);
        this.placeArmy(1, territory);
        this.territories.add(territory);
        return new ActionResponse(true, String.format("%s owns %s", this.getName(),territory.getName()) );
    }

    /**
     *
     * @return list of player territories
     */
    @Override
    public ArrayList<ITerritory> getTerritories() {
        return this.territories;
    }


    /**
     * fancy representation of the player status
     * @return fancy toString
     */
    @Override
    public String toString(){
        String delimiter = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName());
        sb.append(delimiter);
        //sb.append(this.getColor().getName());
        //sb.append(delimiter);
        sb.append("Territories:");
        sb.append(this.getTerritories().size());
        sb.append(delimiter);
        sb.append("Unused Armies:");
        sb.append(this.getUnusedArmies());
        sb.append(delimiter);
        sb.append("Used Armies:");
        sb.append(this.getUsedArmies());
        return sb.toString();
    }

    /**
     *
     * @param count number of armies to be place into the territory
     * @param territory the territory
     * @return if the action is done or not
     */
    @Override
    public ActionResponse placeArmy(int count, ITerritory territory) {

        if(this.unusedArmies - count < 0)
            return new ActionResponse("No sufficient armies.");


        this.setUnusedArmies(this.unusedArmies - count);
        this.setUsedArmies(this.usedArmies + count);
        territory.placeArmies(count);
        LoggerController.log(this.getName() + " placed " + Integer.toString(count)+" armies into " + territory.getName());
        LoggerController.log(this.getName() + " Unused armies = " + Integer.toString(this.getUnusedArmies()) +
                ", Used armies = " + Integer.toString(this.getUsedArmies()) );
        return new ActionResponse(true, String.format("%d armies placed in %s", count, territory.getName()));
    }

    /**
     * another fancy representation of player status
     * @return table
     */
    @Override
    public String getState()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n====================");
        sb.append("\n");
        sb.append(this.getName());
        sb.append("\n");
        sb.append("--------------------");
        sb.append("\n");
        sb.append("Armies: ");
        sb.append("\n");
        sb.append("   Used: ");
        sb.append(this.getUsedArmies());
        sb.append("\n");
        sb.append("   Unused: ");
        sb.append(this.getUnusedArmies());
        sb.append("\n--------------------");
        sb.append("\n");
        sb.append("Territories: ");
        sb.append("\n");
        for(ITerritory t: this.territories)
        {
            sb.append("   "+t.getName());
            sb.append(", ");
            sb.append("Armies : ");
            sb.append(t.getArmies());
            sb.append("\n");
        }
        sb.append("\n");

        sb.append("====================");
        return sb.toString();
    }

    /**
     * finds a territory by its name
     * @param territoryName name
     * @return the territory
     */
    @Override
    public ITerritory getTerritoryByName(String territoryName)
    {
        ITerritory result = null;
        for(ITerritory t:this.territories)
            if(t.getName().equalsIgnoreCase(territoryName))
                result = t;
        return result;

    }

    /**
     *
     * @return randomly selected territory
     */
    @Override
    public ITerritory getRandomTerritory() {
        int max = this.getTerritories().size()-1;
        return this.getTerritories().get(util.Helpers.getRandomInt(max,0));
    }


    /**
     * move armies from a territory to another
     * @param from origin territory
     * @param to destination territory
     * @param number number of armies
     * @return if the operation is done or not
     */
    @Override
    public ActionResponse moveArmies(ITerritory from, ITerritory to, int number) {
        ActionResponse result = new ActionResponse();

        if(from.hasAdjacencyWith(to))
        {
            LoggerController.log(this.getState());
            ActionResponse r = from.removeArmies(number);
            if (r.getOk())
            {
                to.placeArmies(number);
                LoggerController.log(String.format("%s moved %s armies from %s to %s.", this.getName(),
                        number, from.getName(),to.getName()));
                LoggerController.log(this.getState());
            }
        }
        else
        {
            LoggerController.log(LogMessageEnum.ERROT, String.format(
                    "%s wanted to move %s armies from %s to %s, but there is no adjacencies.", this.getName()
                    , number, from.getName(), to.getName() ));
        }

        return result;
    }

    @Override
    public void setGameManager(GameManager gm) {
        this.gm = gm;
    }

    @Override
    public GameManager getGameManager() {
        return this.gm;
    }

    /**
     * Handles reinforcement phase by :
     * calculating the number of armies each player should get
     * let the given player decide where to place the given armies
     */
    public void reinforcement()
    {
        LoggerController.log(String.format("============%s REINFORCEMENT STARTS===========", this.getName()));

        //Step 1: Reinforcement
        int newArmies = this.gm.calculateReinforcementArmies(this);
        this.setUnusedArmies(newArmies);

        //Step 2: Place armies
        this.gm.placeArmies(this);
        LoggerController.log(String.format("============%s REINFORCEMENT DONE===========", this.getName()));
    }


    /**
     * This will handle attack phase but not implemented yet
     */
    public void attack()
    {
        //todo: Implement attach phase.
        LoggerController.log(String.format("============%s ATTACK STARTS===========", this.getName()));
        LoggerController.log(LogMessageEnum.WARNING, "Jump from attack phase. :)");
        LoggerController.log(String.format("============%s ATTACK DONE===========", this.getName()));
    }


    /**
     * does the fortification phase and randomly move armies to another territories
     * owned by the player
     */
    public void fortification()
    {
        LoggerController.log(String.format("============%s FORTIFICATION STARTS===========", this.getName()));

        ITerritory from = this.getRandomTerritory();
        ITerritory to;

        ArrayList<ITerritory> neighbours = from.getAdjacentTerritoryObjects();
        if(neighbours.size()>0)
            to = neighbours.get(0);
        else
            to = this.getRandomTerritory();

        int number = Helpers.getRandomInt(from.getArmies(),1);
        this.moveArmies(from, to, number);


        LoggerController.log(String.format("============%s FORTIFICATION DONE===========", this.getName()));
    }


}
