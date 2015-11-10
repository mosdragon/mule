package edu.gatech.cs2340.sharkbait.util;


import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.model.Packable;
import javafx.scene.control.Button;

import java.io.Serializable;

/**
 * Property class.
 * @author Arihan Shah
 * @version 1.0
 */
public class Property implements Serializable, Packable {
    /**
     * initializes a text "mule".
     */
    private static final String MULE = "Mule";

    /**
     * initializes a tile.
     */
    private final Tile tile;

    /**
     * initializes a PropertyType.
     */
    private final PropertyType type;

    /**
     * constructor for property.
     * @param tileHolder the button the property belongs to.
     */
    public Property(final Button tileHolder) {
        tile = new Tile(tileHolder);
        type = tile.getType();
    }

    /**
     * gets the tile of the property.
     * @return the tile of the property
     */
    private Tile getTile() {
        return tile;
    }


    /**
     * checks if tile has a mule.
     * @return true or false based on if the tile has a mule or not
     */
    public final boolean hasMule() {
        String tileText = tile.getText();
        return tileText.contains(MULE);
    }

    /**
     * adds a mule to a tile.
     * @param mule the mule to be added to tile
     */
    public final void addMule(final Mule mule) {
        tile.setText(mule.toString());
    }

    /**
     * determines if a tile is available.
     * @return true if tile is available, false if not available
     */
    public final boolean isAvailable() {
        return tile.isTransparent();
    }

    /**
     * determines if if the player is the owner of the property.
     * @param player the player to check if is owner
     * @return true if the player passed in is the owner, false if not
     */
    public final boolean isOwner(final Player player) {
        String playerColor = player.getColor();
        return tile.containsColor(playerColor);
    }

    /**
     * Pass in a player that is trying to purchase the property.
     * @param player the user trying to purchase the property
     * @return true if player purchase complete, false otherwise
     */
    public final boolean purchase(final Player player) {
        if (isAvailable()) {
            String playerColor = player.getColor();
            tile.setColor(playerColor);
            return true;
        }
        return false;
    }

    @Override
    public final void unpack() {
        getTile().unpack();
        GameDuration.addProperty(getTile().getHolder(), this);
    }


    @Override
    public final String toString() {
        return "Property: " + type.getText();
    }

    /**
     *gets the property type.
     * @return the property type
     */
    public final PropertyType getType() {
        return type;
    }


}
