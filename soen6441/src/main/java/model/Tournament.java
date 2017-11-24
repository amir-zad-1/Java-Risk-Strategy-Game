package model;

import de.vandermeer.asciitable.AsciiTable;
import model.contract.ITournament;

import java.util.ArrayList;

public class Tournament implements ITournament {

    ArrayList<Map> maps;
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
     * @param playerStrategies player strategics e.g. 3 players "r,h,a"
     * @param g number of games
     * @param d number of dice for each game
     */
    @Override
    public void start(ArrayList<Map> maps, int p, String playerStrategies, int g, int d) {

        this.maps = maps;
        setMapsName();

        ArrayList<GameResult> results = new ArrayList<>();
        String playerText = "";
        for(Map m : maps)
        {
            for (int i=0; i<g; i++)
            {
                GameManager gm = new GameManager(m, p, playerStrategies, d);
                String gameName = String.format("Game %s", i+1);
                gm.setName(gameName);
                try
                {
                    gm.start(false);
                    gm.play(true);
                }
                catch (Exception e)
                {

                }

                GameResult res = gm.play(true);
                playerText = gm.getPlayersText();
                res.setGame(gameName);
                results.add(res);
            }
        }

        System.out.println("");
        System.out.print("M: ");
        for(Map m : maps)
            System.out.print(m.getName()+", ");
        System.out.print("\nP: " + playerText);
        System.out.print(String.format("\nG: %s", g));
        System.out.print(String.format("\nD: %s", d));
        System.out.println("");
        System.out.println("");
        System.out.print(getResult(results, maps.size(), g));

    }

    /**
     * get result table
     * @return result table
     */
    @Override
    public String getResult(ArrayList<GameResult> results, int mapCount, int gameCount) {
        StringBuilder sb = new StringBuilder();


        AsciiTable at = new AsciiTable();
        String[] games = new String[gameCount+1];
        games[0] = "";
        at.addRule();
        for(int i=0; i<gameCount; i++)
        {
            games[i+1] = results.get(i).game;
        }
        at.addRow(games);



        for(Map m : this.maps)
        {
            at.addRule();
            String[] cells = new String[gameCount+1];
            cells[0] = m.getName();
            for(int i=0; i<gameCount; i++)
            {
                cells[i+1] = results.get(i).winner;
            }
            at.addRow(cells);

        }

        at.addRule();
        sb.append(at.render());
        return sb.toString();
    }

    private void setMapsName()
    {
        for(int i=0; i<this.maps.size(); i++)
        {
            this.maps.get(i).setName("Map " + (i+1));
        }

    }
}
