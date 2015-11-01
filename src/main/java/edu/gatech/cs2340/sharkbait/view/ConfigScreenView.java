package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
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

//    /**
//     * Possible game states. Must find a better way to implement this
//     */
//    private enum State {
//        NotConfigured, ConfigGame, ConfigPlayers, BeginGame
//    }

    private State gameState;

    private GameConfigView gameConfigView;
    private List<PlayerConfigView> playerConfigViews;


    public void gameStateNotConfigured() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                    .getResource("/fxml/config/game_config.fxml"));

            Parent root = fxmlLoader.load();
            gameConfigView = fxmlLoader.getController();

            configBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameState = State.ConfigGame;
        nextButton.setText("Add Players");
    }

    public void gameStateConfigGame(){
        try {

            gameConfigView.saveConfigs();
            int numPlayers = GameConfigs.getNumPlayers();

            for (int i = 1; i <= numPlayers; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/fxml/config/players_config.fxml"));
                Node playerPrompt = fxmlLoader.load();
                PlayerConfigView playerConfigView = fxmlLoader.getController();

                playerConfigViews.add(playerConfigView);

                playerPrompt.setId("pane" + i);

                infoPane.getItems().add(playerPrompt);
                String defaultName = "Player " + i;
                playerConfigView.selectName.setText(defaultName);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        gameState = State.ConfigPlayers;
        nextButton.setText("Begin Game");
    }

    public void gameStateConfigPlayers() {

        for (PlayerConfigView controller : playerConfigViews) {
            controller.makePlayer();
        }

        infoPane.getItems().clear();

        nextButton.setVisible(false);
        gameState = State.BeginGame;
        mainGameMessage.setText("Loading your game...");

        MasterController.changeSceneToGameMap();
        GameDuration.begin();
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
                    gameStateNotConfigured();
                }
                else if (gameState == State.ConfigGame) {
                    gameStateConfigGame();
                } else if (gameState == State.ConfigPlayers) {
                    gameStateConfigPlayers();
                }
            }
        });
    }
}
