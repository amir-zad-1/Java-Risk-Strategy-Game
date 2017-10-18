package util;

import java.util.ArrayList;

public class Color {

    private String code;
    private String name;

    private static ArrayList<Color> colors = new ArrayList<Color>();
    private int lastIndex = -1;


    public Color()
    {
        this.fillColors();
    }

    private Color(String name, String code)
    {
        this.code = code;
        this.name = name;
    }

    public Color getRandomColor()
    {
        this.lastIndex++;
        return colors.get(this.lastIndex);

    }

    public String getCode(){ return this.code; }
    public String getName(){ return this.name; }

    private void fillColors(){
        colors.add(new Color("White", "#ffffff"));
        colors.add(new Color("Black", "#000000"));
        colors.add(new Color("Red", "#E81123"));
        colors.add(new Color("Green", "#107C10"));
        colors.add(new Color("Blue", "#0063B1"));
        colors.add(new Color("Orange", "#F7630C"));
    }



}
