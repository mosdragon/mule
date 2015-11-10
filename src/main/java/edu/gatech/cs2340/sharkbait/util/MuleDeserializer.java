package edu.gatech.cs2340.sharkbait.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.gatech.cs2340.sharkbait.model.Packer;
import edu.gatech.cs2340.trydent.log.Log;

import java.lang.reflect.Type;

/**
 * Custom deserialization of Mules from JSON.
 */
public final class MuleDeserializer implements JsonDeserializer<Mule> {

  /**
   * type is a field every mule has.
   */
    private static final String FIELD_MULETYPE = "type";

  /**
   * property is a field every mule has.
   */
    private static final String FIELD_PROPERTY = "property";

  @Override
  public Mule deserialize(final JsonElement jsonElement,
                          final Type type, final JsonDeserializationContext
                                  jsonDeserializationContext)
      throws JsonParseException {

    Mule created = null;
    Log.debug(jsonElement.toString());

    if (jsonElement.isJsonObject()) {
      JsonObject json = jsonElement.getAsJsonObject();
      if (json.has(FIELD_MULETYPE) && json.has(FIELD_PROPERTY)) {

        Resource muleType = Packer.unpack(json.get(FIELD_MULETYPE),
              Resource.class);

        Property property = Packer.unpack(json.get(FIELD_PROPERTY),
              Property.class);
          created =  MuleFactory.createMule(property, muleType);
      }
    }

      return created;
  }
}
