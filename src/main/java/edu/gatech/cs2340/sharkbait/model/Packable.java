package edu.gatech.cs2340.sharkbait.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import edu.gatech.cs2340.sharkbait.util.Player;

import java.io.Serializable;

/**
 * Created by osama on 10/25/15.
 *
 * Interface for classes to serialize themselves using JSON
 * Default implementation provided
 */
public interface Packable extends Serializable {

    default String pack() {
        Gson gson = new Gson();
        JsonElement json = gson.toJsonTree(this);
        if (json != null) {
            return json.toString();
        }
        return null;
    }

    default void unpack() {};

}
