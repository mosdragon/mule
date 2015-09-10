package edu.gatech.cs2340.sharkbait;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("screen.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

//        SplitPane splitter = (SplitPane) root.lookup("#splitter_main");
//        AnchorPane canvasPane = (AnchorPane) splitter.lookup("#canvas");
//        ComboBox difficultySelector = (ComboBox) canvasPane.lookup("#difficulty");

        ObservableList<String> options = FXCollections.observableArrayList(
                        "Beginner",
                        "Standard",
                        "Tournament"
                );

//        difficultySelector.setItems(options);


//        difficultySelector.set

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
