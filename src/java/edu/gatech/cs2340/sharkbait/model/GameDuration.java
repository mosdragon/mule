package edu.gatech.cs2340.sharkbait.model;

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
    private static int turn;
    private static GamePhase phase = GamePhase.LandBuyPhase;

    private GameDuration() {
        turn = 1;
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


    public static int getRound() {
        return round;
    }

    public static void endTurn() {
        if (getTurn() >= GameConfigs.getNumPlayers()) {
            if (phase == GamePhase.LandBuyPhase) {
                turn = 0;
                phase = GamePhase.PlayerTurnPhase;
            } else {
                round++;
                setTurn(0);
            }
        }
        setActivePlayer(GameConfigs.players.get(turn));
        turn++;
    }

    public static void endGame(){}
}
