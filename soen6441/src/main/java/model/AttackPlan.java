package model;

import model.contract.ITerritory;

public class AttackPlan {

    ITerritory from, to;


    public AttackPlan(ITerritory from, ITerritory to)
    {
        this.to = to;
        this.from = from;
    }

    public ITerritory getFrom() {return this.from;}
    public ITerritory getTo(){return this.to;}
}
