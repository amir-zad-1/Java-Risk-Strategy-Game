package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;

import java.util.ArrayList;
import java.util.Scanner;

public class Human implements IStrategy {

    @Override
    public int getAttackAttempts() {
        System.out.print("How many times do you want to attack?");
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        return times;
    }

    @Override
    public String getName() {
        return "Human";
    }

    @Override
    public ITerritory getInforcementTerritory(IPlayer p) {

        System.out.print("Which territory do you want to reinforce?");
        Scanner sc = new Scanner(System.in);
        String territoryName = sc.nextLine();
        return p.getTerritoryByName(territoryName);
    }

    @Override
    public int getReinforcementArmies(IPlayer p) {

        System.out.print("How many armies do you want to put into?");
        Scanner sc = new Scanner(System.in);
        int reinforcementArmies = sc.nextInt();
        return reinforcementArmies;
    }

    @Override
    public AttackPlan getAttackPlan(IPlayer p) {
        return null;
    }

    @Override
    public int diceToAttack(IPlayer p) {

        System.out.print("How many dice do you want to use for the attack?");
        Scanner sc = new Scanner(System.in);
        int dice = sc.nextInt();
        return dice;
    }

    @Override
    public int diceToDefend(IPlayer p) {

        System.out.print("How many dice do you want to use to defend?");
        Scanner sc = new Scanner(System.in);
        int dice = sc.nextInt();
        return dice;
    }

    @Override
    public int getMovingArmiesToNewTerritory(IPlayer p) {

        System.out.print("How many armies do you want to move to the new territory?");
        Scanner sc = new Scanner(System.in);
        int armies = sc.nextInt();
        return armies;
    }

    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {

        System.out.print(p.getState());
        System.out.print("\nWhich territory do you want to move armies from?");
        Scanner sc = new Scanner(System.in);
        String territoryName = sc.nextLine();
        return p.getTerritoryByName(territoryName);
    }

    @Override
    public ITerritory getFortificationToTerritory(IPlayer p, ITerritory from) {
        ArrayList<ITerritory> neighbours = from.getAdjacentNeighbours();
        ITerritory to;

        if(neighbours.size()>0)
        {
            System.out.println(String.format("From %s, you can move to these:", from.getName()));
            for(ITerritory t:neighbours)
                System.out.println(String.format("*) %s (current armies: %s)", t.getName(), t.getArmies()));

            System.out.print("Which territory do you want to move to?");
            Scanner sc = new Scanner(System.in);
            String territoryName = sc.nextLine();
            to = p.getTerritoryByName(territoryName);
        }
        else
        {
            System.out.println(String.format("There is no neighbour for %s. Fortification is canceled.", from.getName()));
            System.out.println("To continue press enter.");
            Scanner sc = new Scanner(System.in);
            String tmp = sc.nextLine();
            to = p.getRandomTerritory();
        }

        return to;
    }

    @Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
        System.out.print("How many armies do you want to fortify?");
        Scanner sc = new Scanner(System.in);
        int armies = sc.nextInt();
        return armies;
    }
}