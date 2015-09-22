package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by arihanshah on 9/22/15.
 */
public class Property {
    private PropertyType type;
    private Player owner;

    public Property(PropertyType type, Player owner) {
        this.type = type;
        this.owner = owner;
    }

    public PropertyType getPropertyType() {
        return type;
    }

    public Player getOwner() {
        return owner;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
