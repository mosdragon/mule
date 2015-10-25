package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.util.Difficulty;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;
import edu.gatech.cs2340.trydent.log.Log;

import java.io.Serializable;

/**
 * Created by osama on 9/22/15.
 */
public class Store implements Serializable, Packable {

//    TODO: Add all resource counts
    private int energyCount;
    private int muleCount;
    private int foodCount;
    private int oreCount;

    private static final int ENERGY = Constants.ENERGY;
    private static final int FOOD = Constants.FOOD;
    private static final int ORE = Constants.ORE;
    private static final int MULE = Constants.MULE;
    private static final int ORE_MULE = Constants.ORE_MULE;
    private static final int FOOD_MULE = Constants.FOOD_MULE;
    private static final int ENERGY_MULE = Constants.ENERGY_MULE;
    private static Store instance;

    private Store() {

    }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public static void initializeStore() {

        if (GameConfigs.getInstance().getGameDifficulty() == Difficulty.Beginner) {
            getInstance().energyCount = 16;
            getInstance().foodCount = 16;
            getInstance().oreCount = 0;
            getInstance().muleCount = 25;

//        TODO: Extra Credit initial store amounts for Standard & Tournament difficulties
        } else {
            getInstance().energyCount = 8;
            getInstance().foodCount = 8;
            getInstance().oreCount = 8;
            getInstance().muleCount = 14;
        }
    }

    public static void buyEnergy (Player player) {
        if (player.getMoney() >= (getInstance().ENERGY) && hasEnergy()) {
            player.changeMoney(-getInstance().ENERGY);
            player.changeEnergy(1);
            getInstance().energyCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellEnergy (Player player) {
        if (player.getEnergy() > 0) {
            player.changeEnergy(-1);
            player.changeMoney(getInstance().ENERGY);
            getInstance().energyCount++;
        } else {
            Log.debug("Not enough energy!");
        }
    }

    public static void buyMule(Player player, Resource type) {
        boolean canAfford = false;
        Log.debug("Mule Type: " + type.toString());
        if (hasMules()) {
            if (type == Resource.Ore) {
                if (player.getMoney() >= getInstance().ORE_MULE) {
                    player.changeMoney(-getInstance().ORE_MULE);
                    canAfford = true;
                } else {
                    Log.debug("Not enough money!");
                }
            } else if (type == Resource.Energy) {
                if (player.getMoney() >= getInstance().ENERGY_MULE) {
                    player.changeMoney(-getInstance().ENERGY_MULE);
                    canAfford = true;
                } else {
                    Log.debug("Not enough money!");
                }
            } else {
                if (player.getMoney() >= getInstance().FOOD_MULE) {
                    player.changeMoney(-getInstance().FOOD_MULE);
                    canAfford = true;
                } else {
                    Log.debug("Not enough money!");
                }
            }

            if (canAfford) {
//                player.changeMules(1);
//                Mule mule = new Mule(player, type);
//                p.addMule(p.getMules(), mule);
                getInstance().muleCount--;
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
        if (player.getMoney() >= (getInstance().FOOD) && hasFood()) {
            player.changeMoney(-getInstance().FOOD);
            player.changeFood(1);
            getInstance().foodCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellFood(Player player) {
        if (player.getFood() > 0) {
            player.changeFood(-1);
            player.changeMoney(getInstance().FOOD);
            getInstance().foodCount++;
        } else {
            Log.debug("Not enough food!");
        }
    }

    public static void buyOre(Player player) {
        if (player.getMoney() >= (getInstance().ORE) && hasOre()) {
            player.changeMoney(-getInstance().ORE);
            player.changeOre(1);
            getInstance().oreCount--;
        } else {
            Log.debug("Not enough money!");
        }
    }

    public static void sellOre(Player player) {
        if (player.getOre() > 0) {
            player.changeOre(-1);
            player.changeMoney(getInstance().ORE);
            getInstance().oreCount++;
        } else {
            Log.debug("Not enough Ore!");
        }
    }

    public static boolean hasMules() {
        return getInstance().muleCount > 0;
    }

    public static boolean hasEnergy() {
        return getInstance().energyCount > 0;
    }

    public static boolean hasOre() {
        return getInstance().oreCount > 0;
    }

    public static boolean hasFood() {
        return getInstance().foodCount > 0;
    }

    public static int getEnergyCount() {
        return getInstance().energyCount;
    }

    public static int getFoodCount() {
        return getInstance().foodCount;
    }

    public static int getOreCount() {
        return getInstance().oreCount;
    }

    public static int getMuleCount() {
        return getInstance().muleCount;
    }

    public static int getOreCost() {return Constants.ORE; }

    public static int getFoodCost() {return Constants.FOOD; }

    public static int getEnergyCost() {return Constants.ENERGY; }

    public static int getOreMuleCost() {return Constants.ORE_MULE; }

    public static int getFoodMuleCost() {return Constants.FOOD_MULE; }

    public static int getEnergyMuleCost() {return Constants.ENERGY_MULE; }

    /**
     * Redefine the single instance of a singleton using the provided source
     * @param source, the source object
     */
    public static void unpack(Store source) {
        instance = source;
    }

    /**
     * Redefine the single instance of a singleton using the provided source, which is JSON
     * @param jsonSource
     */
    public static void unpackfromJson(String jsonSource) {
        Store source = Packer.unpack(jsonSource, Store.class);
        unpack(source);
    }

    /**
     * Serialized instance as JSON
     * @return
     */
    public static String packAsJson() {
        return getInstance().pack();
    }

}
