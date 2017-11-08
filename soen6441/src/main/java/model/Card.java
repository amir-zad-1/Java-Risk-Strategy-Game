package model;

/**
 * represents the card entity in the game
 */
public class Card{

    /**
     * {@link #cardName} is the name of the card
     */
    private String cardName;
    
    /**
     * {@link #armyValue} is the number of armies we can get for this card
     */
    private int armyValue = 0;


    /**
     * Constructor to initializes the Card
     * @param new_cardName tells the name of the card
     * @param new_armyValue tells the {@link #armyValue} we want to set
     */
    public Card(String new_cardName, int new_armyValue)
    {
        this.armyValue = new_armyValue;
        this.cardName = new_cardName;
    }

    /**
     * @return the {@link #cardName}
     */
    public String getCardTerritoryName() { return this.cardName; }
    
    /**
     * @return the {@link #armyValue}
     */
    public int getCardValue() { return this.armyValue; }


}
