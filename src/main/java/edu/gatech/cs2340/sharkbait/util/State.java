package edu.gatech.cs2340.sharkbait.util;

import java.io.Serializable;

/**
 * Created by arihanshah on 11/1/15.
 */
public enum State implements Serializable {
    NotConfigured("NotConfigured"),
    ConfigGame("ConfigGame"),
    ConfigPlayers("ConfigPlayers"),
    BeginGame("BeginGame");

    private String name;

    State(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
