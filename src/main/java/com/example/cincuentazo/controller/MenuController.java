package com.example.cincuentazo.controller;

import com.example.cincuentazo.model.Clases.Game;
import com.example.cincuentazo.view.GameStage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.IOException;

public class MenuController{

    private void startGame(int playerCount) throws IOException {
        Game.restartInstance();
        Game game = Game.getInstance();
        for (int i = 0; i <= playerCount; i++) {
            game.newPlayer();
        }
        GameStage.getInstance().changeScene("/com/example/cincuentazo/CincuentazoView.fxml");
    }
    @FXML private void jugar1() throws IOException { startGame(1); }
    @FXML private void jugar2() throws IOException { startGame(2); }
    @FXML private void jugar3() throws IOException { startGame(3); }


    @FXML
    private void onHowToPlayClick() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CÓMO JUGAR");
        alert.setHeaderText(
                """
                        SI PASAS DE 50 PIERDES:\s
                        Todas las cartas con números del 2 al 8 y el 10 suman su número.
                        Todas las cartas con número 9 ni suman ni restan.
                        Todas las cartas con letras J, Q, K restan 10.
                        Todas las cartas con letra A suman 1 o 10, según convenga."""
        );
        alert.setContentText("¡Diviértete jugando!");
        alert.showAndWait();
    }

}
