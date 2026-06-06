package com.example.cincuentazo.controller;

import com.example.cincuentazo.model.Clases.Game;
import com.example.cincuentazo.view.GameStage;
import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController{

    private void startGame(int playerCount) throws IOException {
        Game.restartInstance();
        Game game = Game.getInstance();
        for (int i = 0; i < playerCount; i++) {
            game.newPlayer();
        }
        GameStage.getInstance().changeScene("/com/example/cincuentazo/CincuentazoView.fxml");
    }
    @FXML private void jugar1() throws IOException { startGame(1); }
    @FXML private void jugar2() throws IOException { startGame(2); }
    @FXML private void jugar3() throws IOException { startGame(3); }
}
