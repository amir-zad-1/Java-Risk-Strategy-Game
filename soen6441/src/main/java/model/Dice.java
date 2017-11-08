package model;

import static util.Helpers.getRandomInt;

/**
 * represents Dice
 */
public class Dice {

	
    /**
     * Empty constructor to initialize the Dice object
     */
    public Dice() {

    }

    /**
     *This method gives a random dice value
     * @return int returned rolled dice value
     * @author Amir
     * @version 0.1.0
     */
    public int roll(){
        return getRandomInt(6,1);
    }

}
