package model;

public class GameResult {


    String map;
    String winner;
    String game;

    /**
     * Constructor
     * @param mapName name of the map
     * @param winner winner
     */
    public GameResult(String mapName, String winner)
    {
        this.map = mapName;
        this.winner = winner;
    }

    /**
     * which map
     * @return map name
     */
    public String getMap() { return this.map; }

    /**
     * who won the game
     * @return winner
     */
    public String getWinner() { return this.winner; }

    /**
     * game name
     * @return name
     */
    public String getGame() { return this.game; }

    /**
     * set game name
     * @param game game name
     */
    public void setGame(String game) { this.game = game; }

}
