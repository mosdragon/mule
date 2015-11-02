package edu.gatech.cs2340.sharkbait.util;

import java.io.Serializable;

/**
 * Created by osama on 9/22/15.
 */
public abstract class AbstractMule implements Serializable, Mule {

    private Resource type;
    private Property property;

    public AbstractMule(Property property, Resource type) {
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
        if (!(obj instanceof AbstractMule)) {
            return false;
        }
        AbstractMule other = (AbstractMule) obj;
        return this.type == other.type;
    }

    @Override
    public String toString() {
        return type.toString() + " Mule";
    }
}
