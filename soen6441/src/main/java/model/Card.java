package model;

/**
 * represents the card entity in the game
 */
public class Card{

    private String territoryName;
    private int armyValue = 0;

    /**
     * constructor
     * @param n name of territory
     * @param v value of territory
     */
    public Card(String n, int v)
    {
        this.armyValue = v;
        this.territoryName = n;
    }

    /**
     * return name of territory
     * @return name
     */
    public String getCardTerritoryName()
    { return this.territoryName; }

    /**
     * return value of card
     * @return card value
     */
    public int getCardValue() { return this.armyValue; }


}
