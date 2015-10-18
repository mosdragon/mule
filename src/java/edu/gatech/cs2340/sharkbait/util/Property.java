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
        type = PropertyType.Plains;

        if (tile.getText().contains(PropertyType.Plains.getText())) {
            type = PropertyType.Plains;

        } else if (tile.getText().contains(PropertyType.River.getText())) {
            type = PropertyType.River;

        } else if (tile.getText().contains(PropertyType.Mountain1.getText())) {
            type = PropertyType.Mountain1;

        } else if (tile.getText().contains(PropertyType.Mountain2.getText())) {
            type = PropertyType.Mountain2;

        } else if (tile.getText().contains(PropertyType.Mountain3.getText())) {
            type = PropertyType.Mountain3;
        }

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
        tile.displayOriginalText();
        String muleText = createMuleText(mule);
        String muleAdded = tile.getText() + muleText;
        tile.setText(muleAdded);
    }

    public void removeMule() {
        tile.displayOriginalText();
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
