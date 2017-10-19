package model;


import com.sun.javafx.binding.StringFormatter;
import model.contract.ITerritory;
import model.contract.IPlayer;
import util.ActionResponse;
import util.Color;
import util.LogMessageEnum;
import util.expetion.NoSufficientArmiesExption;
import view.Logger;

import java.util.ArrayList;

/**
 * This is player class
 *
 * @Author Amir
 * @Version 0.1.0
 */
public class Player implements IPlayer {


    private String name;
    private Color color;
    private int unusedArmies = 0;
    private int usedArmies = 0;
    private ArrayList<ITerritory> territories;

    public Player(String name, Color color){
        this.name = name;
        this.color = color;
        this.territories = new ArrayList<>();
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void setName(String newName){
        this.name = newName;
    }


    @Override
    public void setUnusedArmies(int armies) { this.unusedArmies = armies; }

    @Override
    public int getUnusedArmies(){ return this.unusedArmies; }


    @Override
    public void setUsedArmies(int armies) { this.usedArmies = armies; }

    @Override
    public int getUsedArmies(){ return this.usedArmies; }

    public void setColor(Color color){ this.color = color; }
    public Color getColor() { return this.color; }

    @Override
    public ActionResponse ownTerritory(ITerritory territory) {
        territory.setOwner(this);
        this.placeArmy(1, territory);
        this.territories.add(territory);
        return new ActionResponse(true, String.format("%s owns %s", this.getName(),territory.getName()) );
    }

    @Override
    public ArrayList<ITerritory> getTerritories() {
        return this.territories;
    }

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

    @Override
    public ActionResponse placeArmy(int count, ITerritory territory) {

        if(this.unusedArmies - count < 0)
            return new ActionResponse("No sufficient armies.");


        this.setUnusedArmies(this.unusedArmies - count);
        this.setUsedArmies(this.usedArmies + count);
        territory.placeArmies(count);
        Logger.log(this.getName() + " placed " + Integer.toString(count)+" armies into " + territory.getName());
        Logger.log(this.getName() + " Unused armies = " + Integer.toString(this.getUnusedArmies()) +
                ", Used armies = " + Integer.toString(this.getUsedArmies()) );
        return new ActionResponse(true, String.format("%d armies placed in %s", count, territory.getName()));
    }

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

    @Override
    public ITerritory getTerritoryByName(String territoryName)
    {
        ITerritory result = null;
        for(ITerritory t:this.territories)
            if(t.getName().equalsIgnoreCase(territoryName))
                result = t;
        return result;

    }

    @Override
    public int calculateReinforcementArmies() {
        return 0;
    }

    @Override
    public ITerritory getRandomTerritory() {
        int max = this.getTerritories().size()-1;
        return this.getTerritories().get(util.Helpers.getRandomInt(max,0));
    }

    @Override
    public ActionResponse moveArmies(ITerritory from, ITerritory to, int number) {
        ActionResponse result = new ActionResponse();

        if(from.hasAdjacencyWith(to))
        {
            Logger.log(this.getState());
            ActionResponse r = from.removeArmies(number);
            if (r.getOk())
            {
                to.placeArmies(number);
                Logger.log(String.format("%s moved %s armies from %s to %s.", this.getName(),
                        number, from.getName(),to.getName()));
                Logger.log(this.getState());
            }
        }
        else
        {
            Logger.log(LogMessageEnum.ERROT, String.format(
                    "%s wanted to move %s armies from %s to %s, but there is no adjacencies.", this.getName()
                    , number, from.getName(), to.getName() ));
        }

        return result;
    }

}
