package edu.gatech.cs2340.sharkbait.util;

import java.io.Serializable;

/**
 * Created by arihanshah on 11/1/15.
 */
public enum State implements Serializable {
    /**
     * NotConfigured.
     */
    NotConfigured("NotConfigured"),
    /**
     * ConfigGame.
     */
    ConfigGame("ConfigGame"),
    /**
     * ConfigPlayers.
     */
    ConfigPlayers("ConfigPlayers"),
    /**
     * BeginGame.
     */
    BeginGame("BeginGame");
    /**
     * String name.
     */
    private String name;
    /**
     * State constructor.
     * @param nameInput name of the state.
     */
    State(final String nameInput) {
        name = nameInput;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
