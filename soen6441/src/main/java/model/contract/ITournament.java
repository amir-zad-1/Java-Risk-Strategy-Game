package model.contract;

import model.GameResult;
import model.Map;

import java.util.ArrayList;


public interface ITournament {

    void start(ArrayList<Map> maps, int p, String playerStrategies, int g, int d);

    String getResult(ArrayList<GameResult> results, int mapCount, int gameCount);

}
