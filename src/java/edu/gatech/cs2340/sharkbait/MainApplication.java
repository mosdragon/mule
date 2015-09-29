package edu.gatech.cs2340.sharkbait;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MasterController.getInstance().initialize(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
