package edu.gatech.cs2340.sharkbait.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.Serializable;

/**
 * Interface for classes to serialize themselves using JSON.
 * Default implementation provided.
 */
public interface Packable extends Serializable {

  /**
   * Package class using Serializable and GSON library.
   * @return the json string of the object.
   */
    default String pack() {
        Gson gson = new Gson();
        JsonElement json = gson.toJsonTree(this);
        if (json != null) {
            return json.toString();
        }
        return null;
    }

  /**
   * Nothing special to do when deserializing from JSON for most classes.
   */
    default void unpack() {

    }

}
