package edu.gatech.cs2340.sharkbait.model;

import edu.gatech.cs2340.sharkbait.controller.MasterController;

import java.io.Serializable;

/**
 * Created by osama on 11/2/15.
 */
public class GameSave implements Serializable, Comparable<GameSave> {

    private long gameId;
    private long timeStamp;

    private static final String TEMPLATE = "gameId:%d, timeStamp:%d";

    public GameSave() {
        gameId = MasterController.getGameId();
        timeStamp = System.currentTimeMillis();
    }

    public GameSave(long gameId, long timeStamp) {
        this.gameId = gameId;
        this.timeStamp = timeStamp;
    }


    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        String representation = String.format(TEMPLATE, getGameId(), getTimeStamp());
        return representation;
    }

    @Override
    public int compareTo(GameSave o) {
        Long myTimestamp = timeStamp;
        Long otherTimestamp = o.timeStamp;
        return myTimestamp.compareTo(otherTimestamp);
    }
}
