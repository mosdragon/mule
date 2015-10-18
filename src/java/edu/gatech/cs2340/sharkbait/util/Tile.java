package edu.gatech.cs2340.sharkbait.util;

import javafx.scene.control.Button;

import javax.swing.text.html.CSS;

/**
 * Created by osama on 10/15/15.
 */
public class Tile {

    protected static final String CSS_TRANSPARENT = "-fx-background-color:rgba(0,0,0,0);";
    private static final String BG_COLOR = "-fx-background-color:%s;";

    private static final String PLAINS = "plain";
    private static final String MOUNTAIN = "mountain";
    private static final String RIVER = "river";

//    This button is passed in and is serves as the physical grid representation of the tile
//    including color, text, etc
    private Button holder;
    private PropertyType type;
//    Original text in the tile. Ex: "plain", "mountain", or "river"
    private String originalText;


    public Tile(Button holder) {
        this.holder = holder;
        originalText = holder.getText();
        if (originalText.contains(PLAINS)) {
            type = PropertyType.Plains;
        } else if (originalText.contains(MOUNTAIN)) {
//            type = PropertyType.Mountain;
        } else if (originalText.contains(RIVER)) {
            type = PropertyType.River;
        }
    }

    public Button getHolder() {
        return holder;
    }

    public void setHolder(Button holder) {
        this.holder = holder;
    }

    public boolean isTransparent() {
        String styleText = holder.getStyle();
        return styleText.contains(CSS_TRANSPARENT);
    }

    public void makeTransparent(String color) {
        String currentColor = String.format(BG_COLOR, color);
        replaceColor(currentColor, CSS_TRANSPARENT);
    }

    public void setColor(String replacementColor) {
        String replacement = String.format(BG_COLOR, replacementColor);
        replaceColor(CSS_TRANSPARENT, replacement);
    }

    public void replaceColor(String originalColor, String replacementColor) {
        String styleText = holder.getStyle();
        styleText = styleText.replace(originalColor, replacementColor);
        holder.setStyle(styleText);
    }

    public boolean containsColor(String color) {
        String styleText = holder.getStyle();
        return styleText.contains(color);
    }

    public void setText(String text) {
        holder.setText(text);
    }

    public String getText() {
        return holder.getText();
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public void displayOriginalText() {
        holder.setText(originalText);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public boolean equals(Object obj) {
//        Two tiles are the same if they have the same button representing them
        if (obj instanceof Tile) {
            Tile other = (Tile) obj;
            return other.holder == this.holder;
        }
        return false;
    }
}
