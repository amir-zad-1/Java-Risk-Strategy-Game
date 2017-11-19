package model;

import model.contract.ITournament;

import java.util.ArrayList;
import java.util.Map;

public class Tournament implements ITournament {


    /**
     * constructor
     */
    public Tournament()
    {

    }

    /**
     * it starts the tournament
     * @param maps maps to play on
     * @param p number of players
     * @param playerStrategies player strategirs e.g. 3 players "r,h,a"
     * @param g number of games
     * @param d number of dice for each game
     */
    @Override
    public void start(ArrayList<Map> maps, int p, String playerStrategies, int g, int d) {

    }
}
