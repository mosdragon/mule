package edu.gatech.cs2340.sharkbait.model;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by osama on 10/25/15.
 */
public class Packer {

    /**
     * Prevents this class from being initialized
     */
    private Packer() {

    }

    /**
     * Pass in the object and get a JSON string representation
     * @param source, the object to pack/serialize into a JSON string
     * @return
     */
    public static String pack(Packable source) {
        String serialized = null;
        if (source != null) {
            serialized = source.pack();
        }
        return serialized;
    }

    /**
     * Pass in the source for a packable object along with its type and get the unpacked object
     * back
     * @param jsonSource, the JSON string of the object
     * @param type, the class type of the object
     * @param <T>, the type of object that will be returned
     * @return
     */
    public static <T extends Packable> T unpack(String jsonSource, Class<T>
            type) {

        Gson gson = new Gson();
        T unpacked = gson.fromJson(jsonSource, type);
        return unpacked;
    }

}
