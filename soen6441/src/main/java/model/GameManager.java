package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This is the main game manager that controls the game
 * @Author Amir
 * @Version 0.1.0
 */
public class GameManager {

    private static int MIN_PLAYERS = 2;
    private static int MAX_PLAYERS = 6;

    private SingletonData map;
    private int numberOfPlayers = 0;
    private Dice dice;

    public GameManager(int players) {

        this.numberOfPlayers = numberOfPlayers;
        this.dice = new Dice();
    }

    public void newGame() {

        throw new NotImplementedException();
    }

    public void saveGame() {
        throw new NotImplementedException();
    }

    public void loadGame() {
        throw new NotImplementedException();
    }

    public void exitGame() {

        throw new NotImplementedException();
    }

    public void play() {

        throw new NotImplementedException();
    }

}
