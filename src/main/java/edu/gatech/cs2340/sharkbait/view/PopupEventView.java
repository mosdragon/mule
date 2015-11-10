package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Popup even view class.
 */
public class PopupEventView implements Initializable {
  /**
   * Okay button.
   */
  @FXML
  private Button okayButton;
  /**
   * Event text area.
   */
  @FXML
  private TextArea eventText;

  @Override
  public final void initialize(
      final URL location, final ResourceBundle resources) {
    okayButton.setOnMouseClicked(event -> {
      MasterController.resumeTime();
      MasterController.changeSceneToGameMap();
    });
  }

  /**
   * Set text method.
   *
   * @param input input for text.
   */
  public final void setText(final String input) {
    eventText.setWrapText(true);
    eventText.setText(input);
  }
}
