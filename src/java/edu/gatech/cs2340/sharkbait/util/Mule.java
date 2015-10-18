package edu.gatech.cs2340.sharkbait.util;

/**
 * Created by osama on 9/22/15.
 */
public class Mule {

    private Resource type;
    private Property property;

    public Mule(Property property, Resource type) {
        this.property = property;
        this.type = type;
    }

    public Resource getType() {
        return type;
    }

    public void setType(Resource type) {
        this.type = type;
    }

    public Property getProperty() {
        return property;
    }

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
