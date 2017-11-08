package model;

import static util.Helpers.getRandomInt;

/**
 * represents Dice
 */
public class Dice {

    /**
     * Just empty Constructor 
     */
    public Dice() {

    }

    /**
     * This method just returns a random dice value
     * @return int returned rolled dice value
     * @author Amir
     * @version 0.1.0
     */
    public int roll(){
        return getRandomInt(6,1);
    }

}
