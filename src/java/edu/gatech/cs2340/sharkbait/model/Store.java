package edu.gatech.cs2340.sharkbait.model;

import com.oracle.tools.packager.Log;
import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.Mule;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;
import jdk.management.resource.ResourceType;

/**
 * Created by osama on 9/22/15.
 */
public class Store {

//    TODO: Add all resource counts
    private static int energyCount;
    private static int muleCount;
    private static int foodCount;
    private static int oreCount;

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
        if (p.getMoney() >= (ENERGY) && hasEnergy()) {
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

    public static void buyMule(Player p, Resource type) {
        boolean canAfford = false;
        Log.debug("Mule Type: " + type.toString());
        if (hasMules()) {
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
                Mule mule = new Mule(p, type);
                p.addMule(p.getOwnedMules(), mule);
                muleCount--;
            }
        } else {
            Log.debug("Not enough mules");
        }
    }

    public static void sellMule(Player p, Resource type) {

        Mule mule = new Mule(p, type);


        if (type == Resource.Smithore) {
            if (p.getMules() > 0) {
                p.changeMoney(ORE_MULE);
                p.changeMules(-1);
                p.removeMule(p.getOwnedMules(), mule);
                muleCount++;
            } else {
                Log.debug("Not enough mules!");
            }

        } else if (type == Resource.Food) {
            if (p.getMules() > 0) {
                p.changeMoney(FOOD_MULE);
                p.changeMules(-1);
                p.removeMule(p.getOwnedMules(), mule);
                muleCount++;
            } else {
                Log.debug("Not enough mules!");
            }

        } else if (type == Resource.Energy) {
            if (p.getMules() > 0) {
                p.changeMoney(ENERGY_MULE);
                p.changeMules(-1);
                p.removeMule(p.getOwnedMules(), mule);
                muleCount++;
            } else {
                Log.debug("Not enough mules!");
            }
        }
    }

    public static void buyFood (Player p) {
        if (p.getMoney() >= (FOOD) && hasFood()) {
            p.changeMoney(-FOOD);
            p.changeFood(1);
            foodCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellFood(Player p) {
        if (p.getFood() > 0) {
            p.changeFood(-1);
            p.changeMoney(FOOD);
            foodCount++;
        } else {
            Log.debug("Not enough food!");
        }
    }

    public static void buyOre(Player p) {
        if (p.getMoney() >= (ORE) && hasOre()) {
            p.changeMoney(-ORE);
            p.changeOre(1);
            oreCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellOre(Player p) {
        if (p.getOre() > 0) {
            p.changeOre(-1);
            p.changeMoney(ORE);
            oreCount++;
        } else {
            Log.debug("Not enough Ore!");
        }
    }

    public static boolean hasMules() {
        return muleCount > 0;
    }

    public static boolean hasEnergy() {
        return energyCount > 0;
    }

    public static boolean hasOre() {
        return oreCount > 0;
    }

    public static boolean hasFood() {
        return foodCount > 0;
    }

    public static int getEnergyCount() {
        return energyCount;
    }

    public static int getFoodCount() {
        return foodCount;
    }

    public static int getOreCount() {
        return oreCount;
    }

    public static int getMuleCount() {
        return muleCount;
    }
}
