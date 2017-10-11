package util;

import java.util.Random;

public class Helpers {

    /**
     * this method used for rolling a dice and picking a card
     * @param max maximum number of items. e.g. in dice max is 6
     * @param min minimum number of items e.g. in dice min is 1
     * @return int random generated number
     */
    public static int getRandomInt(int max, int min){
        Random rnd = new Random();
        return  rnd.nextInt((max - min) + 1) + min;
    }

}
