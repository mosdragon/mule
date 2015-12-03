package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
import edu.gatech.cs2340.sharkbait.util.State;
import edu.gatech.cs2340.sharkbait.view.ConfigScreenView;
import edu.gatech.cs2340.sharkbait.view.GameConfigView;
import edu.gatech.cs2340.sharkbait.view.PlayerConfigView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

/**
 * Created by arihanshah on 11/1/15.
 */

public class ConfigScreenController {

    public static void endConfigScreen() {
        MasterController.setupGameMap();
        MasterController.changeSceneToGameMap();
        GameDuration.begin();
    }

    public static void gameStateNotConfigured(Pane configBox, Button nextButton) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MasterController.class
                    .getResource("/fxml/config/game_config.fxml"));
            Parent root = fxmlLoader.load();
            ConfigScreenView.setGameConfigView(fxmlLoader.getController());

            configBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConfigScreenView.setGameState(State.ConfigGame);
        nextButton.setText("Add Players");
    }

    public static void gameStateConfigGame(GameConfigView gameConfigView, List<PlayerConfigView> playerConfigViews,
                                           SplitPane infoPane, Button nextButton) {
        try {

            gameConfigView.saveConfigs();
            int numPlayers = GameConfigs.getNumPlayers();

            for (int i = 1; i <= numPlayers; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(MasterController.class
                        .getResource("/fxml/config/players_config.fxml"));
                Node playerPrompt = fxmlLoader.load();
                PlayerConfigView playerConfigView = fxmlLoader.getController();

                playerConfigViews.add(playerConfigView);

                playerPrompt.setId("pane" + i);

                infoPane.getItems().add(playerPrompt);
                String defaultName = "Player " + i;
                playerConfigView.getSelectName().setText(defaultName);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ConfigScreenView.setGameState(State.ConfigPlayers);
        nextButton.setText("Begin Game");
    }

    public static void gameStateConfigPlayers(List<PlayerConfigView> playerConfigViews, SplitPane infoPane,
                                              Button nextButton, Text mainGameMessage) {

        for (PlayerConfigView controller : playerConfigViews) {
            controller.makePlayer();
        }

        infoPane.getItems().clear();

        nextButton.setVisible(false);
        ConfigScreenView.setGameState(State.BeginGame);
        mainGameMessage.setText("Loading your game...");

        ConfigScreenController.endConfigScreen();
    }
}
