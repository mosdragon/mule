package edu.gatech.cs2340.sharkbait;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by osama on 9/23/15.
 */
public class MasterController {

    private static Scene configScene;
    private static Scene gameMapScene;
    private static Scene townMapScene;

    private static Stage gameStage;

    private static MasterController instance;

    private MasterController() {

    }

    public static MasterController getInstance() {
        if (instance == null) {
            instance = new MasterController();
        }
        return instance;
    }



    public void initialize(Stage gameStage) {
        MasterController.gameStage = gameStage;

        try {
            Parent configRoot = new FXMLLoader(getClass().getResource
                    ("view/fxml/config/config_screen.fxml")).load();
            gameStage.setTitle("M.U.L.E");
            configScene = new Scene(configRoot);

            gameStage.setScene(configScene);
            gameStage.show();

            Parent gameMapRoot = new FXMLLoader(getInstance().getClass().getResource
                    ("view/fxml/game_map.fxml")).load();
            gameMapScene = new Scene(gameMapRoot);

            Parent townMapRoot = new FXMLLoader(getInstance().getClass().getResource
                    ("view/fxml/town_map.fxml")).load();
            townMapScene = new Scene(townMapRoot);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeSceneToGameMap() {
        gameStage.setScene(gameMapScene);
        System.out.println(gameStage.getMaxHeight());
        System.out.println(gameStage.getMaxWidth());
    }

    public static void changeSceneToTownMap() {
        gameStage.setScene(townMapScene);
    }

    // Unused atm
    public static void changeSceneToConfig() {
        gameStage.setScene(configScene);
    }
}
