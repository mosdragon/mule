package edu.gatech.cs2340.sharkbait.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import edu.gatech.cs2340.sharkbait.util.Mule;
import edu.gatech.cs2340.sharkbait.util.MuleDeserializer;

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

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Mule.class, new MuleDeserializer());

        Gson gson = builder.create();
        T unpacked = gson.fromJson(jsonSource, type);
        unpacked.unpack();
        return unpacked;
    }

    /**
     * Pass in the source for a packable object along with its type and get the unpacked object
     * back
     * @param jsonSource, the JSON string of the object
     * @param type, the class type of the object
     * @param <T>, the type of object that will be returned
     * @return
     */
    public static <T extends Packable> T unpack(JsonElement jsonSource, Class<T>
            type) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Mule.class, new MuleDeserializer());

        Gson gson = builder.create();
        T unpacked = gson.fromJson(jsonSource, type);
        unpacked.unpack();
        return unpacked;
    }

}
