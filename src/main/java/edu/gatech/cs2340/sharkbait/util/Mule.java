package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Packable;

import java.io.Serializable;

/**
 * Created by osama on 9/22/15.
 */
public abstract class Mule implements Serializable, Packable, MuleInterface {

    private Resource type;
    private Property property;

    public Mule(Property property, Resource type) {
        this.property = property;
        this.type = type;
    }

    @Override
    public Resource getType() {
        return type;
    }

    @Override
    public void setType(Resource type) {
        this.type = type;
    }

    @Override
    public Property getProperty() {
        return property;
    }

    @Override
    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Mule)) {
            return false;
        }
        Mule other = (Mule) obj;
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return type.toString() + " Mule";
    }
}
