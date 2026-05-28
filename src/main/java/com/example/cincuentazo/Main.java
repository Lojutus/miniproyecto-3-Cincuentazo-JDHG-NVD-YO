package com.example.cincuentazo;

import com.example.cincuentazo.view.GameStage;
import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX application entry point.
 *
 * <p>This class bootstraps the UI by creating the main {@link com.example.cincuentazo.view.GameStage}
 * singleton and showing the initial scene.</p>
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        GameStage.getInstance();

    }

    public static void main(String[] args) {
        launch(args);

    }

}
