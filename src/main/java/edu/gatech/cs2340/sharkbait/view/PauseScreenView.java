package edu.gatech.cs2340.sharkbait.view;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.GameSave;
import edu.gatech.cs2340.sharkbait.model.LocalGameSaves;
import edu.gatech.cs2340.sharkbait.model.MongoPersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Pause screen view class.
 */
public class PauseScreenView implements Initializable {

    /**
     * Save button.
     */
    @FXML
    private Button saveButton;
    /**
     * Resume button.
     */
    @FXML
    private Button resumeButton;
    /**
     * Load button.
     */
    @FXML
    private Button loadButton;


    @Override
    public final void initialize(
            final URL location, final ResourceBundle resources) {
        saveButton.setOnMouseClicked(event -> {
            //DO save
            GameSave save = new GameSave();
            LocalGameSaves.addGameSave(save);
            MongoPersistence.saveGame();
        });

        resumeButton.setOnMouseClicked(event -> {
            MasterController.resumeTime();
            MasterController.changeSceneToGameMap();
        });

        loadButton.setOnMouseClicked(event -> {
            //Load game
            List<GameSave> gameSaves = LocalGameSaves.loadGameSaves();
            if (gameSaves.size() > 0) {
                Collections.sort(gameSaves);
                Collections.reverse(gameSaves);
                GameSave gameSave = gameSaves.get(0);
                MongoPersistence.loadGame(gameSave);
            }
        });
    }
}
