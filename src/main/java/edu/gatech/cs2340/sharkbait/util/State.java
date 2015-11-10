package edu.gatech.cs2340.sharkbait.util;

import java.io.Serializable;

/**
 * Created on 11/1/15 by arihanshah.
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
     * State constructor.
     * @param nameInput name of the state.
     */
    State(final String nameInput) {
        /*
      String name.
     */
    }

//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
