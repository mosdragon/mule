package edu.gatech.cs2340.sharkbait.util;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.sharkbait.model.Packable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.Serializable;


/**
 * Tile class.
 *
 * @author Osama
 */
public class Tile implements Serializable, Packable {

  /**
   * initializes String CSS_TRANSPARENT.
   */
  private static final String CSS_TRANSPARENT = Constants.CSS_TRANSPARENT;
  /**
   * initializes String BG_COLOR_TEMPLATE.
   */
  private static final String BG_COLOR_TEMPLATE = Constants.BG_COLOR_TEMPLATE;
  /**
   * initializes String PLAIN.
   */
  private static final String PLAIN = "plain";
  /**
   * initializes String river.
   */
  private static final String RIVER = "river";
  /**
   * initializes String MOUNTAIN1.
   */
  private static final String MOUNTAIN1 = "mountain1";
  /**
   * initializes String MOUNTAIN2.
   */
  private static final String MOUNTAIN2 = "mountain2";
  /**
   * initializes String MOUNTAIN3.
   */
  private static final String MOUNTAIN3 = "mountain3";

  /**
   * This button is passed in and is serves as the
   * physical grid representation of the tile
   * including color, text, etc.
   */
  private transient Button holder;
  /**
   * initializes PropertyType type.
   */
  private PropertyType type;

  /**
   * initializes an int row for row in grid.
   */
  private final int row;
  /**
   * initializes an int column for column in grid.
   */
  private final int column;

  /**
   * initializes a String tileStyle which is the css style of the tile.
   */
  private String tileStyle;
  /**
   * initializes a String tileText which is the text of the tile.
   */
  private String tileText;

  /**
   * initializes a Tile with a button holder.
   *
   * @param holderButton the button holder of the tile
   */
  public Tile(final Button holderButton) {
    holder = holderButton;
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
  }

  /**
   * sets tileStyle to String passed in.
   *
   * @param tileStyleInput the style to set the tileStyle to
   */
  private void setTileStyle(final String tileStyleInput) {
    holder.setStyle(tileStyleInput);
    tileStyle = tileStyleInput;
  }

  /**
   * goes through gridPane at specified column and row and gets the node.
   *
   * @param gridPane the gridPane to go through
   * @param row      the row index of the grid
   * @param column   the column index of te grid
   * @return the button on the grid at the specified row and column
   */
  private static Button getButtonFromGrid(final GridPane gridPane,
                                          final int row, final int column) {
    for (Node node : gridPane.getChildren()) {
      if (GridPane.getColumnIndex(node) == column
          && GridPane.getRowIndex(node) == row) {
        if (node instanceof Button) {
          return (Button) node;
        }
      }
    }
    return null;
  }

  /**
   * gets the button holder of the tile.
   *
   * @return the Button holder
   */
  public final Button getHolder() {
    return holder;
  }

  /**
   * checks if Tile is transparent.
   *
   * @return true if Tile is transparent, false if not
   */
  public final boolean isTransparent() {
    String styleText = holder.getStyle();
    return styleText.contains(CSS_TRANSPARENT);
  }

  /**
   * sets the color of Tile to color passed in.
   *
   * @param replacementColor the color to change the Tile color too
   */
  public final void setColor(final String replacementColor) {
    String replacement = String.format(BG_COLOR_TEMPLATE, replacementColor);
    replaceColor(replacement);
  }

  /**
   * replaces the color of Tile to  replacement color passed in.
   *
   * @param replacementColor the color to replace Tile color too
   */
  private void replaceColor(final String replacementColor) {
    String styleText = holder.getStyle();
    styleText = styleText.replace(CSS_TRANSPARENT, replacementColor);
    holder.setStyle(styleText);
    this.tileStyle = styleText;
  }

  /**
   * checks if Tile is a certain color.
   *
   * @param color the color to check if the Tile is that color
   * @return true if color passed in is the color of the Tile, false if not.
   */
  public final boolean containsColor(final String color) {
    String styleText = holder.getStyle();
    return styleText.contains(color);
  }

  /**
   * sets the text of the Tile to text passed in.
   *
   * @param text the text to set the Tile too.
   */
  public final void setText(final String text) {
    holder.setText(text);
    this.tileText = text;
  }

  /**
   * gets the text of the Tile.
   *
   * @return the text of the Tile
   */
  public final String getText() {
    return holder.getText();
  }

  /**
   * gets the PropertyType of the Tile.
   *
   * @return the PropertyType of the Tile
   */
  public final PropertyType getType() {
    return type;
  }

  @Override
  public final void unpack() {
    GridPane gridPane = MasterController.getGrid();
    Button holderFromGrid = getButtonFromGrid(gridPane, row, column);
    if (holderFromGrid != null) {
      this.holder = holderFromGrid;
      setText(tileText);
      setTileStyle(tileStyle);
    }
  }

  @Override
  public final String toString() {
    return type.toString();
  }
}
