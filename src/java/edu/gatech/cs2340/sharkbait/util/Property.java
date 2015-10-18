package edu.gatech.cs2340.sharkbait.util;


import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.scene.control.Button;

/**
 * Created by arihanshah on 9/22/15.
 */
public class Property {

    private static final String MULE = "Mule";
    private static final String MULE_TEMPLATE = "\n%s "+ MULE;

    private Tile tile;
    private PropertyType type;

    public Property(Button tileHolder) {
        tile = new Tile(tileHolder);
        type = tile.getType();
        Log.debug(toString());
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public boolean hasMule() {
        String tileText = tile.getText();
        return tileText.contains(MULE);
    }

    public void addMule(Mule mule) {
        String muleText = createMuleText(mule);
        tile.setText(muleText);
    }

    public void removeMule() {
        tile.setText("");
    }

    public boolean isAvailable() {
        return tile.isTransparent();
    }

    public boolean isOwner(Player player) {
        String playerColor = player.getColor();
        return tile.containsColor(playerColor);
    }

    /**
     * Pass in a player that is trying to purchase the property
     * @param player, the user trying to purchase the property
     * @return true if player purchase complete, false otherwise
     */
    public boolean purchase(Player player) {
        if (isAvailable()) {
            String playerColor = player.getColor();
            tile.setColor(playerColor);
            return true;
        }
        return false;
    }

    /**
     * Pass in a player that is trying to sell the property
     * @param player, the user trying to sell the property
     * @return true if property sale complete, false otherwise
     */
    public boolean sell(Player player) {
        if (!isAvailable() && isOwner(player)) {
            String playerColor = player.getColor();
            tile.makeTransparent(playerColor);
            return true;
        }
        return false;
    }

    private String createMuleText(Mule mule) {
        return String.format(MULE_TEMPLATE, mule.getType());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Property) {
            Property other = (Property) obj;
            return this.tile.equals(other.tile);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Property: " + type.getText();
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }
}
