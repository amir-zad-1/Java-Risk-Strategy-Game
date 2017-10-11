package model;


/**
 * This is player class
 *
 * @Author Amir
 * @Version 0.1.0
 */
public class Player {


    private String name;
    private String color;

    public Player(String name){
        this.name = name;
    }


    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

}
