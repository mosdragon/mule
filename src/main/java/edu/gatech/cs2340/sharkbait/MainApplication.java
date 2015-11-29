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
        String path = "./src/music3.wav";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.onRepeatProperty();
        mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
        MediaView mediaView = new MediaView(mediaPlayer);



    }

    public static void main(String[] args) {
        launch(args);
    }
}
