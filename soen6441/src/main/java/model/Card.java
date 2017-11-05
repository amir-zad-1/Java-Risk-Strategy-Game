package model;

/**
 * represents the card entity in the game
 */
public class Card{

    private String territoryName;
    private int armyValue = 0;

    public Card(String n, int v)
    {
        this.armyValue = v;
        this.territoryName = n;
    }

    public String getCardTerritoryName() { return this.territoryName; }
    public int getCardValue() { return this.armyValue; }


}
