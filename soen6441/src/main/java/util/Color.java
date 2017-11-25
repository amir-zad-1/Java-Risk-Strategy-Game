package util;

import java.io.Serializable;
import java.util.ArrayList;

/** Color Manager class provides some method
 * for using colors.
 */
public class Color implements Serializable {

    
	private static final long serialVersionUID = -1296864052319635206L;
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

    /**
     * provides a random color
     * @return color
     */
    public Color getRandomColor()
    {
        this.lastIndex++;
        return colors.get(this.lastIndex);

    }

    /**
     * returns hexa decimal code of the color
     * @return hexa decimal code of the color. like #232323
     */
    public String getCode(){ return this.code; }

    /**
     * returns name of the color
     * @return name of the color. like Green
     */
    public String getName(){ return this.name; }

    /**
     * fills color repository
     */
    private void fillColors(){
        colors.add(new Color("White", "#ffffff"));
        colors.add(new Color("Black", "#000000"));
        colors.add(new Color("Red", "#E81123"));
        colors.add(new Color("Green", "#107C10"));
        colors.add(new Color("Blue", "#0063B1"));
        colors.add(new Color("Orange", "#F7630C"));
    }



}
