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
        boolean available = button.getStyle().contains(CSS_TRANSPARENT);
        boolean isLandBuyPhase = GameDuration.getPhase() == GamePhase.LandBuyPhase;
        boolean isMuleBuyPhase = GameDuration.getPhase() == GamePhase.MulePlacementPhase;

        if (available && isLandBuyPhase) {

            Player activePlayer = GameDuration.getActivePlayer();
            String type = button.getText().toLowerCase();
            PropertyType propertyType = null;

            if (activePlayer != null) {

                if (type.toLowerCase().contains("plain")) {
                    propertyType = PropertyType.Plains;

                } else if (type.contains("mountain")) {
                    propertyType = PropertyType.Mountain;

                } else if (type.contains("river")) {
                    propertyType = PropertyType.River;
                }

                Property property = new Property(propertyType, activePlayer);
                activePlayer.addProperty(property);
                Log.debug("Type: " + type);
                Log.debug("Added " + propertyType.toString()  + " property to player " + activePlayer
                        .getName());

                String color = activePlayer.getColor();
                String styleVal = BG_COLOR + color;
                button.setStyle(styleVal);

                GameDuration.endTurn();
            }

        } else if (isMuleBuyPhase) {
//            Else if bought and bought by active player and in mule buy phase do this

            String buttonStyle = button.getStyle().toLowerCase();
            Player activePlayer = GameDuration.getActivePlayer();
            String playerColor = activePlayer.getColor().toLowerCase();
            boolean isOwnedByPlayer = buttonStyle.contains(playerColor);

            boolean noMuleHere = !button.getText().contains(" Mule");
            boolean tileBought = !available;

            if (tileBought && isOwnedByPlayer && noMuleHere) {
//                Add muletext to button
                Mule mockMule = new Mule(null, GameDuration.getActiveMuleType());
                button.setText(button.getText() + "\n" + mockMule.toString());
                Log.debug(mockMule.toString() + " successfully place for player " + activePlayer
                        .toString());

            } else {
                Log.debug("Player messed up. Lost MULE. Sorry");
            }

            GameDuration.clearActiveMuleType();
            GameDuration.endMulePlacementPhase();
        }

    }
}
