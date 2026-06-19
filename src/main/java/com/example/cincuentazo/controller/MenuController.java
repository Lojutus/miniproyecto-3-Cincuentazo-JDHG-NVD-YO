package com.example.cincuentazo.controller;

import com.example.cincuentazo.model.Clases.Game;
import com.example.cincuentazo.model.Clases.Machine;
import com.example.cincuentazo.model.Clases.Player;
import com.example.cincuentazo.view.GameStage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.io.IOException;

/**
 * Controller responsible for the main menu view.
 *
 * This class allows the user to start a new game with a selected
 * number of machine opponents and provides access to the game rules.
 * @author Nerie
 * @version 1.0
 */
public class MenuController{

    /**
     * Creates a new game session and initializes the requested players.
     *
     * @param playerCount number of machine opponents
     * @throws IOException if the game scene cannot be loaded
     */
    private void startGame(int playerCount) throws IOException {
        Game.restartInstance();
        Game game = Game.getInstance();
        for (int i = 0; i < playerCount; i++) {
            if (i == 0) game.newPlayer(new Player());
            game.newPlayer(new Machine());
        }
        GameStage.getInstance().changeScene("/com/example/cincuentazo/CincuentazoView.fxml");
    }
    /**
     * Starts a game against one machine.
     *
     * @throws IOException if the game scene cannot be loaded
     */
    @FXML private void jugar1() throws IOException { startGame(1); }
    /**
     * Starts a game against two machines.
     *
     * @throws IOException if the game scene cannot be loaded
     */
    @FXML private void jugar2() throws IOException { startGame(2); }
    /**
     * Starts a game against three machines.
     *
     * @throws IOException if the game scene cannot be loaded
     */
    @FXML private void jugar3() throws IOException { startGame(3); }

    /**
     * Displays the game rules and instructions.
     */
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
