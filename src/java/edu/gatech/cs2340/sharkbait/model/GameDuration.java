package edu.gatech.cs2340.sharkbait.model;

import com.sun.org.apache.bcel.internal.generic.LAND;
import edu.gatech.cs2340.sharkbait.controller.GameConfigController;
import edu.gatech.cs2340.sharkbait.util.GamePhase;
import edu.gatech.cs2340.sharkbait.util.Player;
import javafx.scene.Parent;

/**
 * Created by osama on 9/22/15.
 */
public class GameDuration {

    private static Parent gameMap = null;
    private static Parent townMap = null;
    private static Player activePlayer = null;
    private static int round;
    private static int turn = 0;
    private static GamePhase phase = GamePhase.LandBuyPhase;

    private GameDuration() {
    }


    public static Player getActivePlayer() {
        if (activePlayer == null) {
            activePlayer = GameConfigs.getInstance().getPlayers().get(turn);
        }
        return activePlayer;
    }

    public static void setActivePlayer(Player activePlayer) {
        GameDuration.activePlayer = activePlayer;
    }

    public static Parent getGameMap() {
        return gameMap;
    }

    public static void setGameMap(Parent gameMap) {
        GameDuration.gameMap = gameMap;
    }

    public static Parent getTownMap() {
        return townMap;
    }

    public static void setTownMap(Parent townMap) {
        GameDuration.townMap = townMap;
    }


    public static int getTurn() {
        return turn;
    }
    public static void setTurn (int number) {
        turn = number;
    }


    public static int getRound() {
        return round;
    }

    public static void endTurn() {
        turn++;
        if (turn >= GameConfigs.getNumPlayers()) {
            turn = 0;
            if (phase == GamePhase.LandBuyPhase) {
                phase = GamePhase.PlayerTurnPhase;
            } else {
                phase = GamePhase.LandBuyPhase;
                round++;
            }
        }
        activePlayer = GameConfigs.getInstance().getPlayers().get(turn);
    }




    public static void endGame(){}
}
