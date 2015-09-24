package edu.gatech.cs2340.sharkbait;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/fxml/config/config_screen.fxml"));
//        Parent root = fxmlLoader.load();
//
//        primaryStage.setTitle("M.U.L.E");
//        Scene scene = new Scene(root);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();

        MasterController.getInstance().initialize(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
