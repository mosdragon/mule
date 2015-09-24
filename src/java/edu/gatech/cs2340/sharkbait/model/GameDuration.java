package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.controller.GameConfigController;
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
    private static int turn =1;

    private GameDuration() {

    }


    public static Player getActivePlayer() {
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


    public int getRound() {
        return round;
    }

    public static void endTurn() {
        setActivePlayer(GameConfigs.players.get(turn));
        turn++;
        if (getTurn()>= GameConfigs.getNumPlayers()) {
            round++;
            setTurn(0);
        }
    }

    public static void endGame(){}
}
