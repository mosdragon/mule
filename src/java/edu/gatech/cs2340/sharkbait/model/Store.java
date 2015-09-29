package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.util.Mule;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;

/**
 * Created by osama on 9/22/15.
 */
public class Store {

//    TODO: Add all resource counts
    static int energyCount = 16;
    static int muleCount = 25;
    static int foodCount = 16;
    static int oreCount = 0;

    static final int energyPrice = 25;
    static final int foodPrice = 30;
    static final int oreMulePrice = 175;
    static final int foodMulePrice = 125;
    static final int energyMulePrice = 150;
    static final int orePrice = 50;

    private Store() {

    }

    public static void buyEnergy (Player p) {
        if (p.getMoney() >= (energyPrice)) {
            p.changeMoney(-energyPrice);
            p.changeEnergy(1);
            energyCount--;
        } else {
            System.out.println("Not enough money!");
        }
    }

    public static void sellEnergy (Player p) {
        if (p.getEnergy() > 0) {
            p.changeEnergy(-1);
            p.changeMoney(energyPrice);
            energyCount++;
        } else {
            System.out.println("Not enough energy!");
        }
    }

    public static void buyMules (Player p, Resource type) {
        if (type.name() == "Smithore") {
            if (p.getMoney() >= oreMulePrice) {
                p.changeMoney(-oreMulePrice);
                p.changeMules(1);
                Mule temp = new Mule(p, type);
                p.addMule(p.getOwnedMules(), temp);
                muleCount--;
            } else {
                System.out.println("Not enough Money!");
            }
        } else if (type.name() == "Energy") {
            if (p.getMoney() >= energyMulePrice) {
                p.changeMoney(-energyMulePrice);
                p.changeMules(1);
                Mule temp = new Mule(p, type);
                p.addMule(p.getOwnedMules(), temp);
                muleCount--;
            } else {
                System.out.println("Not enough Money!");
            }
        } else {
            if (p.getMoney() >= foodMulePrice) {
                p.changeMoney(-foodMulePrice);
                p.changeMules(1);
                Mule temp = new Mule(p, type);
                p.addMule(p.getOwnedMules(), temp);
                muleCount--;
            } else {
                System.out.println("Not enough Money!");
            }
        }
    }

    public static void sellMule (Player p, Resource type) {
        if (type.name() == "Smithore") {
            if (p.getMules() > 0) {
                p.changeMoney(oreMulePrice);
                p.changeMules(-1);
//              removing the mule from the player
                muleCount++;
            } else {
                System.out.println("Not enough Money!");
            }
        } else if (type.name() == "Food") {
            if (p.getMules() > 0) {
                p.changeMoney(foodMulePrice);
                p.changeMules(-1);
//              removing the mule from the player
                muleCount++;
            } else {
                System.out.println("Not enough Money!");
            }
        } else if (type.name() == "Energy") {
            if (p.getMules() > 0) {
                p.changeMoney(energyMulePrice);
                p.changeMules(-1);
//              removing the mule from the player
                muleCount++;
            } else {
                System.out.println("Not enough Money!");
            }
        }
    }
    public static void buyFood (Player p) {
        if (p.getMoney() >= (foodPrice)) {
            p.changeMoney(-foodPrice);
            p.changeFood(1);
            foodCount--;
        } else {
            System.out.println("Not enough money!");
        }
    }
    public static void sellFood (Player p) {
        if (p.getFood() > 0) {
            p.changeFood(-1);
            p.changeMoney(foodPrice);
            foodCount++;
        } else {
            System.out.println("Not enough Food!");
        }
    }
    public static void buyOre (Player p) {
        if (p.getMoney() >= (orePrice)) {
            p.changeMoney(-orePrice);
            p.changeOre(1);
            oreCount--;
        } else {
            System.out.println("Not enough money!");
        }
    }
    public static void sellOre (Player p) {
        if (p.getOre() > 0) {
            p.changeOre(-1);
            p.changeMoney(orePrice);
            oreCount++;
        } else {
            System.out.println("Not enough Ore!");
        }
    }

}
