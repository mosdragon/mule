package edu.gatech.cs2340.sharkbait.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.MongoPersistence;
import edu.gatech.cs2340.sharkbait.model.Packer;
import edu.gatech.cs2340.sharkbait.util.*;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.scene.control.Button;

import java.lang.reflect.Type;

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
                Mule mule = new Mule(property, GameDuration.getActiveMuleType());
                property.addMule(mule);
                activePlayer.addMule(mule);

            } else {
                Log.debug("Player messed up. Lost MULE. Sorry");
            }


            GameDuration.clearActiveMuleType();
            GameDuration.endMulePlacementPhase();
            MasterController.getInstance().updateMessages();

            //          TODO: Remove serialization code
//            MongoPersistence.saveGame();
            MongoPersistence.loadGame(1445811031661L);
        }

    }
}
