package edu.gatech.cs2340.sharkbait.model;

import com.oracle.tools.packager.Log;
import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.Mule;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;

/**
 * Created by osama on 9/22/15.
 */
public class Store {

//    TODO: Add all resource counts
    private static int energyCount = 16;
    private static int muleCount = 25;
    private static int foodCount = 16;
    private static int oreCount = 0;

    private static final int ENERGY = 25;
    private static final int FOOD = 30;
    private static final int ORE = 50;
    private static final int MULE = 100;
    private static final int ORE_MULE = MULE + ORE;
    private static final int FOOD_MULE = MULE + FOOD;
    private static final int ENERGY_MULE = MULE + ENERGY;

    private Store() {

    }

    public static void initializeStore() {

        if (GameConfigs.getInstance().getGameDifficulty() == Difficulty.Beginner) {
            energyCount = 16;
            foodCount = 16;
            oreCount = 0;
            muleCount = 25;

//            TODO: Extra Credit initial store amounts for Standard & Tournament difficulties
        } else {
            energyCount = 8;
            foodCount = 8;
            oreCount = 8;
            muleCount = 14;
        }
    }

    public static void buyEnergy (Player p) {
        if (p.getMoney() >= (ENERGY)) {
            p.changeMoney(-ENERGY);
            p.changeEnergy(1);
            energyCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellEnergy (Player p) {
        if (p.getEnergy() > 0) {
            p.changeEnergy(-1);
            p.changeMoney(ENERGY);
            energyCount++;
        } else {
            Log.debug("Not enough energy!");
        }
    }

    public static void buyMules (Player p, Resource type) {
        boolean canAfford = false;
        if (type == Resource.Smithore) {
            if (p.getMoney() >= ORE_MULE) {
                p.changeMoney(-ORE_MULE);
                canAfford = true;
            } else {
                Log.debug("Not enough money!");
            }
        } else if (type == Resource.Energy) {
            if (p.getMoney() >= ENERGY_MULE) {
                p.changeMoney(-ENERGY_MULE);
                canAfford = true;
            } else {
                Log.debug("Not enough money!");
            }
        } else {
            if (p.getMoney() >= FOOD_MULE) {
                p.changeMoney(-FOOD_MULE);
                canAfford = true;
            } else {
                Log.debug("Not enough money!");
            }
        }

        if (canAfford) {
            p.changeMules(1);
            Mule temp = new Mule(p, type);
            p.addMule(p.getOwnedMules(), temp);
            muleCount--;
        }
    }

    public static void sellMule(Player p, Mule mule) {

        if (mule.getType() == Resource.Smithore) {
            if (p.getMules() > 0) {
                p.changeMoney(ORE_MULE);
                p.changeMules(-1);
                p.removeMule(p.getOwnedMules(), mule);
                muleCount++;
            } else {
                Log.debug("Not enough money!");
            }

        } else if (mule.getType() == Resource.Food) {
            if (p.getMules() > 0) {
                p.changeMoney(FOOD_MULE);
                p.changeMules(-1);
                p.removeMule(p.getOwnedMules(), mule);
                muleCount++;
            } else {
                Log.debug("Not enough money!");
            }

        } else if (mule.getType() == Resource.Energy) {
            if (p.getMules() > 0) {
                p.changeMoney(ENERGY_MULE);
                p.changeMules(-1);
                p.removeMule(p.getOwnedMules(), mule);
                muleCount++;
            } else {
                Log.debug("Not enough money!");
            }
        }
    }

    public static void buyFood (Player p) {
        if (p.getMoney() >= (FOOD)) {
            p.changeMoney(-FOOD);
            p.changeFood(1);
            foodCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellFood (Player p) {
        if (p.getFood() > 0) {
            p.changeFood(-1);
            p.changeMoney(FOOD);
            foodCount++;
        } else {
            Log.debug("Not enough food!");
        }
    }

    public static void buyOre (Player p) {
        if (p.getMoney() >= (ORE)) {
            p.changeMoney(-ORE);
            p.changeOre(1);
            oreCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellOre (Player p) {
        if (p.getOre() > 0) {
            p.changeOre(-1);
            p.changeMoney(ORE);
            oreCount++;
        } else {
            Log.debug("Not enough Ore!");
        }
    }

}
