package edu.gatech.cs2340.sharkbait;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MasterController.initialize(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
