package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameDuration;


import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Property;
import edu.gatech.cs2340.sharkbait.util.Player;
import edu.gatech.cs2340.sharkbait.util.Resource;
import edu.gatech.cs2340.sharkbait.util.Mule;
import edu.gatech.cs2340.sharkbait.util.MuleFactory;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.scene.control.Button;

/**
 * Controller class for GameMap.
 * Created on 10/8/15.
 * @author Osama
 * @version 1.0
 */
public final class GameMapController {

    /**
     * Constructs a GameMapController.
     */
    private GameMapController() {
    }
    /**
     * if in playerTurnPhase changes the scene to TownMap.
     */
    public static void townClicked() {
        if (GameDuration.getPhase() == GamePhase.PlayerTurnPhase) {
            MasterController.changeSceneToTownMap();
        }
    }
    /**
     * ends turn.
     */
    public static void pass() {
        GameDuration.endTurn();
    }

    /**
     * Attempts to press a gird button.
     * if in landBuyPhase and property is available,
     * sets the property to activePlayer.
     * If in MuleBuyPhase and property is owned by player,
     * sets the mule on the button(property)
     * @param button the button that is clicked
     */
    public static void attemptGridButtonClick(final Button button) {

        Property property = GameDuration.fetchProperty(button);
        boolean available = property.isAvailable();

        boolean isLandBuyPhase = GameDuration.getPhase()
                == GamePhase.LandBuyPhase;
        boolean isMulePlacementPhase = GameDuration.getPhase()
                == GamePhase.MulePlacementPhase;

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
//       Else if bought and owned by active player and in mule buy phase do this

            Player activePlayer = GameDuration.getActivePlayer();

            boolean isOwnedByPlayer = property.isOwner(activePlayer);
            boolean noMuleHere = !property.hasMule();
            boolean tileBought = !available;

            if (tileBought && isOwnedByPlayer && noMuleHere) {

                Resource muleType = GameDuration.getActiveMuleType();
                Mule mule = MuleFactory.createMule(property, muleType);

                property.addMule(mule);
                activePlayer.addMule(mule);

            } else {
                Log.debug("Player messed up. Lost MULE. Sorry");
            }

            GameDuration.clearActiveMuleType();
            GameDuration.endMulePlacementPhase();
            MasterController.updateMessages();

        }

    }
}
