package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.util.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * ProductionView class.
 */
public class ProductionView implements Initializable {

  /**
   * Okay button.
   */
  @FXML
  private Button okayButton;
  /**
   * Player name text field.
   */
  @FXML
  private TextField player1Name;
  /**
   * Player food text field.
   */
  @FXML
  private TextField player1Food;
  /**
   * Player energy text field.
   */
  @FXML
  private TextField player1Energy;
  /**
   * Player ore text field.
   */
  @FXML
  private TextField player1Ore;

  @Override
  public final void initialize(
      final URL location, final ResourceBundle resources) {
    okayButton.setOnMouseClicked(event -> {
      MasterController.resumeTime();
      MasterController.changeSceneToGameMap();
    });
  }

  /**
   * Sets the player production.
   *
   * @param player the current player
   */
  public final void setPlayerProduction(final Player player) {
    player1Name.setText(player.getName());
    player1Food.setText(Integer.toString(player.getFood()));
    player1Energy.setText(Integer.toString(player.getEnergy()));
    player1Ore.setText(Integer.toString(player.getOre()));
  }
}
