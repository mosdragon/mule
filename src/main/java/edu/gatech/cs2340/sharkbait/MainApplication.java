package edu.gatech.cs2340.sharkbait;

import edu.gatech.cs2340.sharkbait.controller.MasterController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point for the entire Mule game.
 */
public final class MainApplication extends Application {
  /**
   * Starts the application.
   *
   * @param primaryStage, a stage generated using JavaFX Application class.
   * @throws Exception the exception thrown if app goes wack
   */
  @Override
  public void start(final Stage primaryStage) throws Exception {
    MasterController.initialize(primaryStage);
  }

  /**
   * Runs and instantiates application.
   *
   * @param args, the args
   */
  public static void main(final String[] args) {
    launch(args);
  }
}
