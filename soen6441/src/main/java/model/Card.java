package model;

import java.io.Serializable;

/**
 * represents the card entity in the game
 */
public class Card implements Serializable{

	/**
	 * {@link #serialVersionUID} used during deserialization to verify that the sender and receiver 
	 * of a serialized object have loaded classes for that object that are compatible with respect to 
	 * serialization. If the receiver has loaded a class for the object that has a different {@link #serialVersionUID}
	 */
	private static final long serialVersionUID = 1012842278301009514L;

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
    public String getCardName() { return this.cardName; }
    
    /**
     * @return the {@link #armyValue}
     */
    public int getCardValue() { return this.armyValue; }


}
