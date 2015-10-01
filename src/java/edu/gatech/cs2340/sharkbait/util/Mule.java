package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by osama on 9/22/15.
 */
public class Mule {

    private Resource type;
    private Player owner;

    public Mule (Player owner, Resource type) {
        this.owner = owner;
        this.type = type;
    }

    public Resource getType() {
        return type;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setType(Resource type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Mule)) {
            return false;
        }
        Mule other = (Mule) obj;
        return this.type == other.type;
    }
}
