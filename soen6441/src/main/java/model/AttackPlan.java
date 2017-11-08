package model;

import model.contract.ITerritory;

/**
 * @author Amir
 * This class is used as a holder for both
 * attacker and defender territories while doing an attack
 */
public class AttackPlan {

    /**
     * {@link #from} is the attacker and 
     * {@link #to} is the defender
     */
    ITerritory from, to;


    /**
     * Constructor that initializes both attacker and defender territories 
     * @param from is the attacker territory of instance {@link ITerritory} 
     * @param to is the defender territory of instance {@link ITerritory}
     */
    public AttackPlan(ITerritory from, ITerritory to)
    {
        this.to = to;
        this.from = from;
    }

    
    /**
     * @return the attacker {@link Territory}
     */
    public ITerritory getFrom() {return this.from;}
    
    
    /**
     * @return the defender {@link Territory}
     */
    public ITerritory getTo(){return this.to;}

}
