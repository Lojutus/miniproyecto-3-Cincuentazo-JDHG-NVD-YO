package com.example.cincuentazo.controller;

import com.example.cincuentazo.controller.helpers.CardViewManager;
import com.example.cincuentazo.controller.helpers.HandSpritesHelper;
import com.example.cincuentazo.model.AbstractsClases.AbstractPlayer;
import com.example.cincuentazo.model.Clases.Game;
import com.example.cincuentazo.model.Clases.Machine;
import com.example.cincuentazo.model.Clases.Player;
import com.example.cincuentazo.model.Exceptions.InvalidCardException;
import com.example.cincuentazo.model.Exceptions.InvalidPlayersException;
import com.example.cincuentazo.model.Exceptions.MachinePlayException;
import com.example.cincuentazo.model.Exceptions.MachineThreadException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class GameController {
    public ImageView two;
    public ImageView one;
    public ImageView three;
    public ImageView four;

    public Label sum;

    public HBox machine1;
    public HBox machine2;
    public HBox machine3;

    HandSpritesHelper cartsSprites;
    CardViewManager spriteUpdaterHelper = new CardViewManager();

    String actualCard;

    int maxPlayer;
    int turn = 0;

    ImageView selectedCard;
    public ImageView lastCardImage;


    void updateSum() {
        sum.setText(String.valueOf(Game.getInstance().getSum()));

    }

    Boolean sentCard(String card) {
        return Game.getInstance().add(card);
    }

    @FXML
    public void initialize() {
        if (Game.getInstance().getPlayers() < 1) {
            Game.getInstance().newPlayer(new Player());
        }
        Game.getInstance().initGame();
        spriteUpdaterHelper.updateCard(lastCardImage, Game.getInstance().getLastCard());
        maxPlayer = Game.getInstance().getPlayers();
        cartsSprites = new HandSpritesHelper(one, two, three, four);
        spriteUpdaterHelper.updateDeck(Game.getInstance().getPlayer(0), cartsSprites);

        showActivePlayers();
        Platform.runLater(() -> {
            one.getScene().setOnKeyPressed(event -> {
                if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                    sendInput(null);
                }
            });
        });
    }

    private void showActivePlayers() {
        int players = Game.getInstance().getPlayers();
        if (players == 1) {
            throw new InvalidPlayersException(players);
        }
        if (players >= 2) {
            machine1.setDisable(false);
            machine1.setOpacity(1);
        }
        if (players >= 3) {
            machine2.setDisable(false);
            machine2.setOpacity(1);
        }
        if (players >= 4) {
            machine3.setDisable(false);
            machine3.setOpacity(1);
        }
        if (players > 4) {
            throw new InvalidPlayersException(players);
        }


    }

    private void disablePlayers(int player) {
        if (player == 2) {
            machine1.setDisable(true);
            machine1.setOpacity(0.2);
        }
        if (player == 3) {
            machine2.setDisable(true);
            machine2.setOpacity(0.2);
        }
        if (player == 4) {
            machine3.setDisable(true);
            machine3.setOpacity(0.2);
        }

    }

    @FXML
    public void selectCard(MouseEvent event) {
        if (turn != 0) return;
        spriteUpdaterHelper.clean(selectedCard);
        selectedCard = (ImageView) event.getSource();
        spriteUpdaterHelper.clean(lastCardImage);
        spriteUpdaterHelper.clickEffect(selectedCard, lastCardImage, event);
        actualCard = selectedCard.getUserData().toString();
        updateSum();

    }

    @FXML
    public void sendInput(MouseEvent mouseEvent) {
        if (turn == 0 && selectedCard != null) {
            try{
                if (!sentCard(actualCard)) throw new InvalidCardException(actualCard);

                spriteUpdaterHelper.updateCard(lastCardImage, actualCard);
                updateSum();
                Game.getInstance().changeHandCard(turn, actualCard);
                spriteUpdaterHelper.updateDeck(Game.getInstance().getPlayer(0), cartsSprites);

                changeTurn();


            } catch (InvalidCardException e){
                if (!Game.getInstance().checkLose()) {

                    Alert alert = new Alert(Alert.AlertType.WARNING);

                    alert.setTitle("Warning");
                    alert.setHeaderText("CUIDADO");
                    alert.setContentText("ESTA CARTA NO ES VALIDA");

                    alert.showAndWait();
                    return;
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("GAME OVER");
                alert.setHeaderText("Has perdido");
                alert.setContentText("Las maquinas continuaran el juego");
                alert.showAndWait();
                Game.getInstance().playerLose(0);
                spriteUpdaterHelper.hideDeck(cartsSprites);

                changeTurn();

            }

        }
        else{
            changeTurn();
        }

    }

    private void changeTurn() {
        spriteUpdaterHelper.clean(selectedCard);
        selectedCard = null;


        turn++;
        if (turn >= Game.getInstance().getPlayers()) {
            if (!Game.getInstance().getPlayer(0).playing){
                turn = 1;
                machineTurn();
                return;
            }
            turn = 0;
            return;
        }
        machineTurn();
    }

    private void machineTurn() {

        AbstractPlayer player = Game.getInstance().getPlayer(turn);

        if (!player.playing) { //Se elimino la maquina
            changeTurn();
            return;
        }

        new Thread(() -> {
            try {
                Thread.sleep(2000 + (long)(Math.random() * 2000)); //  2-4s para jugar
            } catch (InterruptedException e) {
                throw new MachineThreadException(turn, e);
            }

            Platform.runLater(() -> {
                try {
                    String card = askMachine();
                    if (card == null) throw new MachinePlayException(turn);

                    if (!Game.getInstance().add(card)) throw new MachinePlayException(turn);

                    spriteUpdaterHelper.updateCard(lastCardImage, card);
                    updateSum();

                    new Thread(() -> {
                        try {
                            Thread.sleep(1000 + (long) (Math.random() * 1000)); // HU-4: 1-2s para tomar carta
                        } catch (InterruptedException e) {
                            throw new MachineThreadException(turn, e);
                        }

                        Platform.runLater(() -> {
                            Game.getInstance().changeHandCard(turn, card);
                            changeTurn();
                        });
                    }).start();
                }catch (MachinePlayException e){
                    Game.getInstance().playerLose(turn); //Se elimina
                    disablePlayers(turn); //Se vuelve opaco
                    if (Game.getInstance().checkWin()) {
                        int winner = Game.getInstance().getWinnerIndex();

                        if (winner == 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);

                            alert.setTitle("Felicidades");
                            alert.setHeaderText("Has ganado");
                            alert.setContentText("Eres un larper del poker");
                            alert.showAndWait();

                        } else {
                            if (winner != -1){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                                alert.setTitle("GAME OVER");
                                alert.setHeaderText("Una maquina ha ganado");
                                alert.setContentText("Felicidades, a la maquina #" + winner);
                                alert.showAndWait();
                            }
                        }

                        return;
                    }
                    changeTurn();
                }
            });
        }).start();
    }
    private String askMachine ()
    {
        AbstractPlayer player = Game.getInstance().getPlayer(turn);

        if (player instanceof Machine machine) {
            return machine.think(Game.getInstance().getSum());
        }

        return null;
    }

}


