package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.*;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.scene.control.Button;

/**
 * Created by osama on 10/8/15.
 */
public class GameMapController {

    //    Used for Grid Button Press
    private static final String CSS_TRANSPARENT = "-fx-background-color:rgba(0,0,0,0);";
    private static final String BG_COLOR = "-fx-background-color:";

    private static final String PLAINS = "plain";
    private static final String MOUNTAIN = "mountain";
    private static final String RIVER = "river";

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
        boolean isMuleBuyPhase = GameDuration.getPhase() == GamePhase.MulePlacementPhase;

        if (available && isLandBuyPhase) {

            Player activePlayer = GameDuration.getActivePlayer();

            if (activePlayer != null) {

                activePlayer.purchaseProperty(property);

                String color = activePlayer.getColor();
                String styleVal = BG_COLOR + color;
                button.setStyle(styleVal);

                GameDuration.endTurn();
            }

        } else if (isMuleBuyPhase) {
//            Else if bought and bought by active player and in mule buy phase do this

            Player activePlayer = GameDuration.getActivePlayer();

            boolean isOwnedByPlayer = property.isOwner(activePlayer);
            boolean noMuleHere = !property.hasMule();
            boolean tileBought = !available;

            if (tileBought && isOwnedByPlayer && noMuleHere) {

                Mule mule = new Mule(null, GameDuration.getActiveMuleType());
                property.addMule(mule);

            } else {
                Log.debug("Player messed up. Lost MULE. Sorry");
            }

            GameDuration.clearActiveMuleType();
            GameDuration.endMulePlacementPhase();
            MasterController.updateMessages();
        }

    }
}
