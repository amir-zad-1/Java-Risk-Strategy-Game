package model;

import static util.Helpers.getRandomInt;

/**
 * represents Dice
 */
public class Dice {

    /**
     * constructor
     */
    public Dice() {

    }

    /**
     *
     * @return int returned rolled dice value
     * @author Amir
     * @version 0.1.0
     */
    public int roll(){
        return getRandomInt(6,1);
    }

}
