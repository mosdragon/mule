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

    @FXML
    private Pane configBox;
    @FXML
    private Button nextButton;
    @FXML
    private SplitPane infoPane;
    @FXML private Text mainGameMessage;


    private static State gameState;

    private static GameConfigView gameConfigView;
    private List<PlayerConfigView> playerConfigViews;

    public static void setGameState(State gS) {
        gameState = gS;
    }

    public static void setGameConfigView(GameConfigView gCV) {
        gameConfigView = gCV;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameState = State.NotConfigured;
        nextButton.setText("Configure Game");

        playerConfigViews = new ArrayList<>();

        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                configBox.getChildren().clear();
                if (gameState == State.NotConfigured) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                            .getResource("/fxml/config/players_config.fxml"));
                    ConfigScreenController.gameStateNotConfigured(configBox, nextButton, fxmlLoader);
                }
                else if (gameState == State.ConfigGame) {
                    ConfigScreenController.gameStateConfigGame(gameConfigView, playerConfigViews, infoPane, nextButton);
                } else if (gameState == State.ConfigPlayers) {
                    ConfigScreenController.gameStateConfigPlayers(playerConfigViews, infoPane,
                            nextButton, mainGameMessage);
                }
            }
        });
    }
}
