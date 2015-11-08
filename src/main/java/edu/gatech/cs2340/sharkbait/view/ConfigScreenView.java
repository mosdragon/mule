package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.controller.ConfigScreenController;
import edu.gatech.cs2340.sharkbait.util.State;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by osama on 9/13/15.
 */
public class ConfigScreenView implements Initializable {
    /**
     * Pane configBox.
     */
    @FXML
    private Pane configBox;
    /**
     * Button nextButton.
     */
    @FXML
    private Button nextButton;
    /**
     * SplitPane infoPane.
     */
    @FXML
    private SplitPane infoPane;
    /**
     * Text mainGameMessage.
     */
    @FXML private Text mainGameMessage;
    /**
     * State gameState.
     */
    private static State gameState;
    /**
     * GameConfigView gameConfigView.
     */
    private static GameConfigView gameConfigView;
    /**
     * List<PlayerConfigView> playerConfigViews.
     */
    private List<PlayerConfigView> playerConfigViews;
    /**
     * setGameState method.
     * @param gS gameState
     */
    public static void setGameState(final State gS) {
        gameState = gS;
    }
    /**
     * gameConfigView method.
     * @param gCV gameConfigView
     */
    public static void setGameConfigView(final GameConfigView gCV) {
        gameConfigView = gCV;
    }

    @Override
    public final void initialize(final URL location,
                                 final ResourceBundle resources) {
        gameState = State.NotConfigured;
        nextButton.setText("Configure Game");

        playerConfigViews = new ArrayList<>();

        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                configBox.getChildren().clear();
                if (gameState == State.NotConfigured) {
                    ConfigScreenController.gameStateNotConfigured(configBox,
                            nextButton);
                } else if (gameState == State.ConfigGame) {
                    ConfigScreenController.gameStateConfigGame(gameConfigView,
                            playerConfigViews, infoPane, nextButton);
                } else if (gameState == State.ConfigPlayers) {
                    ConfigScreenController.gameStateConfigPlayers(
                            playerConfigViews, infoPane, nextButton,
                            mainGameMessage);
                }
            }
        });
    }
}
