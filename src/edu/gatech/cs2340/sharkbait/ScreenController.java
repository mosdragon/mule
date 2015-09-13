package edu.gatech.cs2340.sharkbait;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    private enum State {
        NotSConfigured, ConfigGame, ConfigPlayers, BeginGame
    }

    private State gameState;

    private GameConfigController gameConfigController;
    private List<PlayerAddController> playerAddControllers;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameState = State.NotSConfigured;
        nextButton.setText("Configure Game");

        playerAddControllers = new ArrayList<>();

        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                final GameConfigs configs = GameConfigs.getInstance();
//                int numPlayers = (int) playerSlider.getValue();
//                System.out.println(numPlayers);
//                Difficulty difficulty = configDifficulty.getValue();
//                System.out.println(difficulty);
//                MapType mapType = mapChoice.getValue();
//                System.out.println(mapType);
//
//                configs.setGameDifficulty(difficulty);
//                configs.setMapType(mapType);
//                configs.setNumPlayers(numPlayers);

                configBox.getChildren().clear();
                if (gameState == State.NotSConfigured) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                                .getResource("gameConfig.fxml"));

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
//                        Node root = FXMLLoader.load(getClass().getResource(
//                                "info_pane_holder.fxml"));
//
//                        infoPane.getChildren().add(root);
                        GameConfigs configs = GameConfigs.getInstance();
                        gameConfigController.saveConfigs();
                        int numPlayers = configs.getNumPlayers();
//                        <fx:include source="players.fxml" fx:id="player1"/>
//
                        for (int i = 1; i <= numPlayers; i++) {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                                    .getResource("players.fxml"));
                            Node playerPrompt = fxmlLoader.load();
                            PlayerAddController playerAddController = fxmlLoader.getController();

                            playerAddControllers.add(playerAddController);

                            playerPrompt.setId("pane" + i);
                            System.out.println(playerPrompt.getId());

//                            infoPane.getChildrenUnmodifiable();
                            infoPane.getItems().add(playerPrompt);

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    gameState = State.ConfigPlayers;
                    nextButton.setText("Begin Game");
                } else if (gameState == State.ConfigPlayers) {

                    List<Player> players = new ArrayList<Player>();

                    int i = 1;
                    for (PlayerAddController controller : playerAddControllers) {
                        String defaultName = "Player" + i;
                        players.add(controller.addPlayer(defaultName));
                        i++;
                    }

                    GameConfigs configs = GameConfigs.getInstance();
                    configs.setPlayers(players);

                    infoPane.getItems().clear();

                    nextButton.setVisible(false);
                    gameState = State.BeginGame;
                    mainGameMessage.setText("Loading your game...");
                }

            }
        });
    }
}
