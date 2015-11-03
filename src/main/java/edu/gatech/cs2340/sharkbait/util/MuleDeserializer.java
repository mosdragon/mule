package edu.gatech.cs2340.sharkbait.util;

import com.google.gson.*;
import edu.gatech.cs2340.sharkbait.model.Packer;
import edu.gatech.cs2340.trydent.log.Log;

import java.lang.reflect.Type;

/**
 * Created by osama on 11/3/15.
 */
public class MuleDeserializer implements JsonDeserializer<Mule> {

    private static final String FIELD_MULETYPE = "type";
    private static final String FIELD_PROPERTY = "property";

    @Override
    public Mule deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext
            jsonDeserializationContext) throws JsonParseException {

        Mule created = null;
        Log.debug(jsonElement.toString());

        if (jsonElement.isJsonObject()) {

            JsonObject json = jsonElement.getAsJsonObject();
            if (json.has(FIELD_MULETYPE) && json.has(FIELD_PROPERTY)) {
                Resource muleType = Packer.unpack(json.get(FIELD_MULETYPE), Resource
                        .class);
                Property property = Packer.unpack(json.get(FIELD_PROPERTY), Property.class);
                created =  MuleFactory.createMule(property, muleType);
            }
        }

        return created;
    }
}
