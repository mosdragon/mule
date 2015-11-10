package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameSave;
import edu.gatech.cs2340.sharkbait.model.LocalGameSaves;
import edu.gatech.cs2340.sharkbait.model.MongoPersistence;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
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

        newGameButton.setOnMouseClicked(event -> {
            MasterController.changeSceneToConfig();
        });

        loadGameButton.setOnMouseClicked(event -> {
            //Load game
            List<GameSave> gameSaves = LocalGameSaves.loadGameSaves();
            if (gameSaves.size() > 0) {
                Log.debug("Loading");
                Collections.sort(gameSaves);
                Collections.reverse(gameSaves);
                GameSave gameSave = gameSaves.get(0);
                MongoPersistence.loadGame(gameSave);
                MasterController.changeSceneToGameMap();

            } else {
                Log.debug("Load failed. Starting new game");
                MasterController.changeSceneToConfig();
            }
        });
    }
}
