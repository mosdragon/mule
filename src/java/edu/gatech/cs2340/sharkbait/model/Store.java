package edu.gatech.cs2340.sharkbait.model;

import com.oracle.tools.packager.Log;
import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;


/**
 * Created by osama on 9/22/15.
 */
public class Store {

//    TODO: Add all resource counts
    private static int energyCount;
    private static int muleCount;
    private static int foodCount;
    private static int oreCount;

    private static final int ENERGY = Constants.ENERGY;
    private static final int FOOD = Constants.FOOD;
    private static final int ORE = Constants.ORE;
    private static final int MULE = Constants.MULE;
    private static final int ORE_MULE = Constants.ORE_MULE;
    private static final int FOOD_MULE = Constants.FOOD_MULE;
    private static final int ENERGY_MULE = Constants.ENERGY_MULE;

    private Store() {

    }

    public static void initializeStore() {

        if (GameConfigs.getGameDifficulty() == Difficulty.Beginner) {
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

    public static void buyEnergy (Player player) {
        if (player.getMoney() >= (ENERGY) && hasEnergy()) {
            player.changeMoney(-ENERGY);
            player.changeEnergy(1);
            energyCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellEnergy (Player player) {
        if (player.getEnergy() > 0) {
            player.changeEnergy(-1);
            player.changeMoney(ENERGY);
            energyCount++;
        } else {
            Log.debug("Not enough energy!");
        }
    }

    public static void buyMule(Player player, Resource type) {
        boolean canAfford = false;
        Log.debug("Mule Type: " + type.toString());
        if (hasMules()) {
            if (type == Resource.Ore) {
                if (player.getMoney() >= ORE_MULE) {
                    player.changeMoney(-ORE_MULE);
                    canAfford = true;
                } else {
                    Log.debug("Not enough money!");
                }
            } else if (type == Resource.Energy) {
                if (player.getMoney() >= ENERGY_MULE) {
                    player.changeMoney(-ENERGY_MULE);
                    canAfford = true;
                } else {
                    Log.debug("Not enough money!");
                }
            } else {
                if (player.getMoney() >= FOOD_MULE) {
                    player.changeMoney(-FOOD_MULE);
                    canAfford = true;
                } else {
                    Log.debug("Not enough money!");
                }
            }

            if (canAfford) {
//                player.changeMules(1);
//                Mule mule = new Mule(player, type);
//                p.addMule(p.getMules(), mule);
                muleCount--;
            }
        } else {
            Log.debug("Not enough mules");
        }
    }

    public static void sellMule(Player player, Resource type) {

//        Mule mule = new Mule(player, type);
//
//
//        if (type == Resource.Ore) {
//            if (player.getMuleCount() > 0) {
//                player.changeMoney(ORE_MULE);
//                player.removeMule(mule);
//                muleCount++;
//            } else {
//                Log.debug("Not enough mules!");
//            }
//
//        } else if (type == Resource.Food) {
//            if (player.getMuleCount() > 0) {
//                player.changeMoney(FOOD_MULE);
//                player.removeMule(mule);
//                muleCount++;
//            } else {
//                Log.debug("Not enough mules!");
//            }
//
//        } else if (type == Resource.Energy) {
//            if (player.getMuleCount() > 0) {
//                player.changeMoney(ENERGY_MULE);
//                player.removeMule(mule);
//                muleCount++;
//            } else {
//                Log.debug("Not enough mules!");
//            }
//        }
    }

    public static void buyFood (Player player) {
        if (player.getMoney() >= (FOOD) && hasFood()) {
            player.changeMoney(-FOOD);
            player.changeFood(1);
            foodCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellFood(Player player) {
        if (player.getFood() > 0) {
            player.changeFood(-1);
            player.changeMoney(FOOD);
            foodCount++;
        } else {
            Log.debug("Not enough food!");
        }
    }

    public static void buyOre(Player player) {
        if (player.getMoney() >= (ORE) && hasOre()) {
            player.changeMoney(-ORE);
            player.changeOre(1);
            oreCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellOre(Player player) {
        if (player.getOre() > 0) {
            player.changeOre(-1);
            player.changeMoney(ORE);
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
