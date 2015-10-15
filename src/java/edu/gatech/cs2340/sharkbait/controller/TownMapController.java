package edu.gatech.cs2340.sharkbait.controller;

import com.sun.xml.internal.ws.api.config.management.policy.ManagedServiceAssertion;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.Store;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;
import edu.gatech.cs2340.trydent.log.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by osama on 10/7/15.
 */
public class TownMapController {

    private static final List<Integer> ROUND_BONUS = new ArrayList<>(Arrays.asList(50, 50, 50, 100,
            100, 100, 100, 150, 150, 150, 150, 200));

    public static void enterPub() {
        Random rand = new Random();
        int timeRemaining = GameDuration.getTimeRemaining();
        int timeBonus = rand.nextInt(timeRemaining + 1);

        Player player = GameDuration.getActivePlayer();

        int moneyBonus = ROUND_BONUS.get(GameDuration.getRound() - 1) * timeBonus;
        Log.debug("Bonus: " + moneyBonus);
        player.changeMoney(moneyBonus);

        MasterController.changeSceneToGameMap();
        GameDuration.endTurn();

    }

    public static void buyOre() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.buyOre(activePlayer);
    }

    public static void sellOre() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.sellOre(activePlayer);
    }

    public static void buyEnergy() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.buyEnergy(activePlayer);
    }

    public static void sellEnergy() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.sellEnergy(activePlayer);
    }

    public static void buyFood() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.buyFood(activePlayer);
    }

    public static void sellFood() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.sellFood(activePlayer);
    }

    private static void purchasedMule() {
        GameDuration.beginMulePlacementPhase();
        MasterController.changeSceneToGameMap();
    }


    public static void buyOreMule() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.buyMule(activePlayer, Resource.Smithore);
        GameDuration.setActiveMuleType(Resource.Smithore);
        purchasedMule();
    }

    public static void sellOreMule() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.sellMule(activePlayer, Resource.Smithore);
        GameDuration.setActiveMuleType(Resource.Smithore);
    }

    public static void buyFoodMule() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.buyMule(activePlayer, Resource.Food);
        GameDuration.setActiveMuleType(Resource.Food);
        purchasedMule();
    }

    public static void sellFoodMule() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.sellMule(activePlayer, Resource.Food);
        GameDuration.setActiveMuleType(Resource.Food);
    }

    public static void buyEnergyMule() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.buyMule(activePlayer, Resource.Energy);
        GameDuration.setActiveMuleType(Resource.Energy);
        purchasedMule();
    }

    public static void sellEnergyMule() {
        Player activePlayer = GameDuration.getActivePlayer();
        Store.sellMule(activePlayer, Resource.Energy);
        GameDuration.setActiveMuleType(Resource.Energy);
    }

    public static void exitTown() {
        MasterController.changeSceneToGameMap();
    }
}
