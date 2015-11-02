package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


import javax.swing.text.Style;
import javax.swing.text.html.CSS;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;


/**
 * Created by osama on 10/15/15.
 */
public class Tile implements Serializable {

    private static final String CSS_TRANSPARENT = Constants.CSS_TRANSPARENT;
    private static final String BG_COLOR_TEMPLATE = Constants.BG_COLOR_TEMPLATE;

    private static final String PLAIN = "plain";
    private static final String RIVER = "river";
    private static final String MOUNTAIN1 = "mountain1";
    private static final String MOUNTAIN2 = "mountain2";
    private static final String MOUNTAIN3 = "mountain3";

//    This button is passed in and is serves as the physical grid representation of the tile
//    including color, text, etc
    private transient Button holder;
    private PropertyType type;

    private int row;
    private int column;



    private String tileStyle;
    private String tileText;



    public Tile(Button holder) {
        this.holder = holder;
        String buttonClass = holder.getStyleClass().toString();
        if (buttonClass.contains(PLAIN)) {
            type = PropertyType.Plains;

        } else if (buttonClass.contains(RIVER)) {
            type = PropertyType.River;

        } else if (buttonClass.contains(MOUNTAIN1)) {
            type = PropertyType.Mountain1;

        } else if (buttonClass.contains(MOUNTAIN2)) {
            type = PropertyType.Mountain2;

        } else if (buttonClass.contains(MOUNTAIN3)) {
            type = PropertyType.Mountain3;
        }
        this.row = GridPane.getRowIndex(holder);
        this.column = GridPane.getColumnIndex(holder);
        this.tileStyle = holder.getStyle();
        this.tileText = holder.getText();

//        Log.debug(String.format("Row: %d\tCol: %d\tTitleStyle: %s\tTileText: %s", row, column, tileStyle, tileText));

    }


    // goes thru gridPane at specified column and row and gets the node
    private Button getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Button)node;
            }
        }
        return null;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getTileStyle() {
        return tileStyle;
    }

    public String getTileText() {
        return tileText;
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
        String currentColor = String.format(BG_COLOR_TEMPLATE, color);
        replaceColor(currentColor, CSS_TRANSPARENT);
    }

    public void setColor(String replacementColor) {
        String replacement = String.format(BG_COLOR_TEMPLATE, replacementColor);
        replaceColor(CSS_TRANSPARENT, replacement);
    }

    public void replaceColor(String originalColor, String replacementColor) {
        String styleText = holder.getStyle();
        styleText = styleText.replace(originalColor, replacementColor);
        holder.setStyle(styleText);
        this.tileStyle = styleText;
    }

    public boolean containsColor(String color) {
        String styleText = holder.getStyle();
        return styleText.contains(color);
    }

    public void setText(String text) {
        holder.setText(text);
        this.tileText = text;
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
