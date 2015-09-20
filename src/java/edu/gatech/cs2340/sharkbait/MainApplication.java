package edu.gatech.cs2340.sharkbait;

import edu.gatech.cs2340.sharkbait.controller.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private ScreenController controller;

    public static String BASE = "../../../../..";

    @Override
    public void start(Stage primaryStage) throws Exception {
//        ResourceBundle.getBundle();
//        getClass().getResource();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/fxml/map.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        primaryStage.setTitle("Hello World");
        Scene configScene = new Scene(root);
        primaryStage.setScene(configScene);

//        Parent map = null;
//        Scene mapScene = new Scene(map);
//        primaryStage.setScene(mapScene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
