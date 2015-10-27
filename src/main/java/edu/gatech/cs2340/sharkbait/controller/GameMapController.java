package edu.gatech.cs2340.sharkbait.controller;

import com.google.gson.Gson;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.*;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.scene.control.Button;

/**
 * Created by osama on 10/8/15.
 */
public class GameMapController {

    public static void townClicked() {
        if (GameDuration.getPhase() == GamePhase.PlayerTurnPhase) {
            MasterController.changeSceneToTownMap();
        }
    }

    public static void pass() {
        GameDuration.endTurn();
    }

    public static void attemptGridButtonClick(Button button) {

        Property property = GameDuration.fetchProperty(button);
        boolean available = property.isAvailable();

        boolean isLandBuyPhase = GameDuration.getPhase() == GamePhase.LandBuyPhase;
        boolean isMulePlacementPhase = GameDuration.getPhase() == GamePhase.MulePlacementPhase;

        Player deserializedPlayer = null;

        if (isLandBuyPhase) {

            if (available) {
                Player activePlayer = GameDuration.getActivePlayer();
                boolean purchased = activePlayer.purchaseProperty(property);

                if (purchased) {
                    GameDuration.endTurn();
                } else {
                    Log.debug("Purchase incomplete");
                }
            } else {
                Log.debug("Property unavailable");
            }

        } else if (isMulePlacementPhase) {
//            Else if bought and owned by active player and in mule buy phase do this

            Player activePlayer = GameDuration.getActivePlayer();

            boolean isOwnedByPlayer = property.isOwner(activePlayer);
            boolean noMuleHere = !property.hasMule();
            boolean tileBought = !available;

            if (tileBought && isOwnedByPlayer && noMuleHere) {
                Mule mule = null;
                Resource muleType = GameDuration.getActiveMuleType();

                if (muleType == Resource.Food) {
                    mule = new FoodMule(property);

                } else if (muleType == Resource.Ore) {
                    mule = new OreMule(property);

                } else if (muleType == Resource.Energy) {
                    mule = new EnergyMule(property);
                }

                property.addMule(mule);
                activePlayer.addMule(mule);

            } else {
                Log.debug("Player messed up. Lost MULE. Sorry");
            }

//            Serialize
            Gson gson = new Gson();

            String configsJson = GameConfigs.packAsJson();
            Log.debug("Configs: " + configsJson);
            GameConfigs.setNumPlayers(1000);
            Log.debug("Set players 1000: " + GameConfigs.getNumPlayers());
            GameConfigs.unpackfromJson(configsJson);
            Log.debug("Unpacked: " + GameConfigs.getNumPlayers());



            GameDuration.clearActiveMuleType();
            GameDuration.endMulePlacementPhase();
            MasterController.getInstance().updateMessages();
        }

    }
}
