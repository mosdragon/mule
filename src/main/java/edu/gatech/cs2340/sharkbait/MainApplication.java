package edu.gatech.cs2340.sharkbait;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import edu.gatech.cs2340.sharkbait.model.Constants;
import edu.gatech.cs2340.trydent.log.Log;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.File;
import sun.audio.*;


public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MasterController.initialize(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
