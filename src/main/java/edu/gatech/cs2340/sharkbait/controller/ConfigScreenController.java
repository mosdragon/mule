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
 * Created on 11/1/15 by arihanshah.
 */

public class ConfigScreenController {
    /**
     * endConfigScreen method.
     * Changes the scene to GameMap and begins GameDuration.
     */
    private static void endConfigScreen() {
        MasterController.changeSceneToGameMap();
        GameDuration.begin();
    }
    /**
     * endConfigScreen method.
     * Game state for NotConfigured
     * @param configBox The configBox Pane
     * @param nextButton The nextButton Button
     */
    public static void gameStateNotConfigured(final Pane configBox,
                                              final Button nextButton) {
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
    /**
     * gameStateConfigGame method.
     * Game state for ConfigGame
     * @param gameConfigView The configBox Pane
     * @param playerConfigViews The list of PlayerConfigViews
     * @param infoPane The infoPane SplitPane
     * @param nextButton The nextButton Button
     */
    public static void gameStateConfigGame(final GameConfigView gameConfigView,
                                           final List<PlayerConfigView>
                                                   playerConfigViews,
                                           final SplitPane infoPane,
                                           final Button nextButton) {
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
    /**
     * gameStateConfigPlayers method.
     * Game state for ConfigPlayers
     * @param mainGameMessage The mainGameMessageText
     * @param playerConfigViews The list of PlayerConfigViews
     * @param infoPane The infoPane SplitPane
     * @param nextButton The nextButton Button
     */
    public static void gameStateConfigPlayers(final List<PlayerConfigView>
                                                      playerConfigViews,
                                              final SplitPane infoPane,
                                              final Button nextButton,
                                              final Text mainGameMessage) {

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
