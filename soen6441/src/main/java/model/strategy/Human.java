package model.strategy;

import model.AttackPlan;
import model.contract.IPlayer;
import model.contract.IStrategy;
import model.contract.ITerritory;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

/**
 * Human Strategy
 */
public class Human extends Observable implements IStrategy {

	public static Object waiter = new Object();
	
    /**
     * {@link Scanner} to read data from command prompt
     */
    Scanner scanner = new Scanner(System.in);
	
    /**
     * how many attacks
     * @return number of attacks
     */
    @Override
    public int getAttackAttempts() {
        System.out.print("How many times do you want to attack?");
        setChanged();
        notifyObservers("How many times do you want to attack?"); 	
        waitForUserInput();
        System.out.print("How many times do you want to attack?");
        int times = scanner.nextInt();
        return times;
    }

    /**
     * name of the strategy
     * @return name
     */
    @Override
    public String getName() {
        return "Human";
    }

    /**
     * where to reinforce
     * @param p player
     * @return territory
     */
    @Override
    public ITerritory getInforcementTerritory(IPlayer p) {

        System.out.print("Tip: your weakest territory is : ");
        System.out.println(p.getWeakestTerritory().getName());
        System.out.print("Which territory do you want to reinforce?");
        String territoryName = scanner.nextLine();
        return p.getTerritoryByName(territoryName);
    }

    /**
     * set number of reinforcement armies
     * @param p player
     * @return number of reinforcement
     */
    @Override
    public int getReinforcementArmies(IPlayer p) {

        System.out.print("How many armies do you want to put into?");
        int reinforcementArmies = scanner.nextInt();
        return reinforcementArmies;
    }

    /**
     * Generates an attack plan for a player
     * @param p player
     * @return attack plan
     */
    @Override
    public AttackPlan getAttackPlan(IPlayer p)
    {
        AttackPlan result = null;
        System.out.print(p.getState());
        System.out.print("\nTip: your strongest territory is : ");
        ITerritory str = p.getStrongestTerritory();
        System.out.println(str.getName() + "(" + str.getArmies()  +")");
        System.out.print("\nWhich territory do you want to attack from?");
        String territoryName = scanner.nextLine();
        ITerritory from = p.getTerritoryByName(territoryName);

        ArrayList<ITerritory> neighbours = from.getAdjacentNeighbours();
        ITerritory to;

        if(neighbours.size()>0)
        {

            //list neighbors to attack
            ArrayList<ITerritory> attackList = new ArrayList<>();
            for(ITerritory a: from.getAdjacentTerritoryObjects())
            {
                if(a.getOwner() != from.getOwner())
                {
                    attackList.add(a);
                }
            }

            System.out.println(String.format("From %s, you can attack to these:", from.getName()));
            for(ITerritory t:attackList)
                System.out.println(String.format("*) %s (current armies: %s)(%s)", t.getName(), t.getArmies(), t.getOwner().getName()));

            System.out.print("Which territory do you want to attack to?");
            territoryName = scanner.nextLine();
            to = p.getGameManager().getTerritory(territoryName);

            result = new AttackPlan(from, to);
        }
        else
        {
            System.out.println(String.format("There is no neighbour for %s. Attack is canceled.", from.getName()));
            System.out.println("To continue press enter.");
            String tmp = scanner.nextLine();
            result = null;
        }

        return result;

    }

    /**
     * role dice for attacking
     * @param p player
     * @return int dice
     */
    @Override
    public int diceToAttack(IPlayer p) {

        System.out.print("How many dice do you want to use for the attack?");
        int dice = scanner.nextInt();
        return dice;
    }

    /**
     * role dice to defend
     * @param p player
     * @return dice
     */
    @Override
    public int diceToDefend(IPlayer p) {

        System.out.print("How many dice do you want to use to defend?");
        int dice = scanner.nextInt();
        return dice;
    }

    /**
     * how many armies should move to new territory
     * @param p player
     * @return number of armies
     */
    @Override
    public int getMovingArmiesToNewTerritory(IPlayer p) {

        System.out.print("How many armies do you want to move to the new territory?");
        int armies = scanner.nextInt();
        return armies;
    }

    /**
     * where to fortify
     * @param p player
     * @return territory
     */
    @Override
    public ITerritory getFortificationFromTerritory(IPlayer p) {

        System.out.print(p.getState());
        System.out.print("\nTip: your strongest territory is : ");
        ITerritory str = p.getStrongestTerritory();
        ITerritory wea = p.getWeakestTerritory();
        System.out.println(str.getName() + "(" + str.getArmies()  +")");
        System.out.print("Tip: your weakest territory is : ");
        System.out.println(wea.getName() + "(" + wea.getArmies()  +")");

        System.out.print("\nWhich territory do you want to move armies from?");
        Scanner sc = new Scanner(System.in);
        String territoryName = sc.nextLine();
        return p.getTerritoryByName(territoryName);
    }

    /**
     * where to move fortification armies to
     * @param p player
     * @param from origin of fortification
     * @return destination of fortification
     */
    @Override
    public ITerritory getFortificationToTerritory(IPlayer p, ITerritory from) {
        ArrayList<ITerritory> neighbours = new ArrayList<>();
        ArrayList<ITerritory> temp = from.getAdjacentNeighbours();
        for(ITerritory t : temp)
            if(t.getOwner() == p )
                neighbours.add(t);

        ITerritory to;

        if(neighbours.size()>0)
        {
            System.out.println(String.format("From %s, you can move to these:", from.getName()));
            for(ITerritory t:neighbours)
                System.out.println(String.format("*) %s (current armies: %s)(%s)", t.getName(), t.getArmies(),t.getOwner().getName()));

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

    /**
     * number of fortification armes
     * @param p player
     * @param from origin of fortification
     * @return number of armies
     */
    @Override
    public int getFortificationArmies(IPlayer p, ITerritory from) {
        System.out.print("How many armies do you want to fortify?");
        Scanner sc = new Scanner(System.in);
        int armies = sc.nextInt();
        return armies;
    }
    
    
    private void waitForUserInput(){
    	try {
 			waiter.wait();
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}
    }
    
}
