package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameConfigs;
import edu.gatech.cs2340.sharkbait.model.GameDuration;
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
 * Created by sayem on 10/31/15.
 */
public class LoadScreenView implements Initializable {

    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;
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

    private GameConfigView gameConfigView;
    private List<PlayerConfigView> playerConfigViews;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameState = State.NotConfigured;
        newGameButton.setText("New Game");

        playerConfigViews = new ArrayList<>();

        newGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                        .getResource("/fxml/config/config_screen.fxml"));
                gameConfigView = fxmlLoader.getController();
                gameState = State.ConfigGame;
            }
        });
    }
}
