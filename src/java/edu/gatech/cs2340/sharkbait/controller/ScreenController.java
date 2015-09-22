package edu.gatech.cs2340.sharkbait.controller;

import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.util.Player;
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
public class ScreenController implements Initializable {

    @FXML
    private Pane configBox;
    @FXML
    private Button nextButton;
    @FXML
    private SplitPane infoPane;
    @FXML private Text mainGameMessage;

    /**
     * Possible game states. Must find a better way to implement this
     */
    private enum State {
        NotConfigured, ConfigGame, ConfigPlayers, BeginGame
    }

    private State gameState;

    private GameConfigController gameConfigController;
    private List<PlayerConfigController> playerConfigControllers;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameState = State.NotConfigured;
        nextButton.setText("Configure Game");

        playerConfigControllers = new ArrayList<>();

        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                configBox.getChildren().clear();
                if (gameState == State.NotConfigured) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                                .getResource("../view/fxml/gameConfig.fxml"));

                        Parent root = fxmlLoader.load();
                        gameConfigController = fxmlLoader.getController();

                        configBox.getChildren().add(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gameState = State.ConfigGame;
                    nextButton.setText("Add Players");
                }
                else if (gameState == State.ConfigGame) {
                    try {

                        GameConfigs configs = GameConfigs.getInstance();
                        gameConfigController.saveConfigs();
                        int numPlayers = configs.getNumPlayers();

                        for (int i = 1; i <= numPlayers; i++) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                                    .getResource("../view/fxml/players.fxml"));
                            Node playerPrompt = fxmlLoader.load();
                            PlayerConfigController playerConfigController = fxmlLoader.getController();

                            playerConfigControllers.add(playerConfigController);

                            playerPrompt.setId("pane" + i);
                            System.out.println(playerPrompt.getId());

                            infoPane.getItems().add(playerPrompt);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    gameState = State.ConfigPlayers;
                    nextButton.setText("Begin Game");
                } else if (gameState == State.ConfigPlayers) {

                    List<Player> players = new ArrayList<>();

                    int i = 1;
                    for (PlayerConfigController controller : playerConfigControllers) {
                        String defaultName = "Player" + i;
                        players.add(controller.makePlayer(defaultName));
                        i++;
                    }

                    GameConfigs configs = GameConfigs.getInstance();
                    configs.setPlayers(players);

                    infoPane.getItems().clear();

                    nextButton.setVisible(false);
                    gameState = State.BeginGame;
                    mainGameMessage.setText("Loading your game...");

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                            .getResource("../view/fxml/map.fxml"));

                    try {
                        Parent parent = fxmlLoader.load();
                        configBox.getScene().setRoot(parent);
//                        configBox.getScene().
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
