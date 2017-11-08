package model;

/**
 * represents the card entity in the game
 */
public class Card{

    /**
     * <p>{@link #armyValue} holds the armies that can be exchnaged with a
     * Card object </p>
     */
    private int armyValue = 0;
    private String cardName = null;

    /**
     * Constructor that initializes the card value
     * @param new_cardName is to set the card name
     * @param new_armyValue is to set {@link #armyValue}
     */
    public Card(String new_cardName, int new_armyValue)
    {
    	this.cardName = new_cardName;
        this.armyValue = new_armyValue;
    }

    /**
     * @return {@link #armyValue} associated with a card
     */
    public int getCardValue() { return this.armyValue; }

	/**
	 * Return the name of the Crad
	 * @return the {@link #cardName}
	 */
	public String getCardTerritoryName() {
		return this.cardName;
	}


}
