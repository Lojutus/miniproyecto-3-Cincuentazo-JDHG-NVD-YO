package com.example.cincuentazo.controller;

import com.example.cincuentazo.controller.helpers.CardViewManager;
import com.example.cincuentazo.controller.helpers.HandSpritesHelper;
import com.example.cincuentazo.model.AbstractsClasses.AbstractPlayer;
import com.example.cincuentazo.model.Classes.Game;
import com.example.cincuentazo.model.Classes.Machine;
import com.example.cincuentazo.model.Classes.Player;
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

/**
 * Controller responsible for managing the main game view.
 *
 * This class coordinates the interaction between the user interface
 * and the game logic. It handles card selection, turn management,
 * machine actions, player elimination, and visual updates during
 * the match.
 * @author José David Hurtado
 * @version 1.0
 */
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

    /**
     * Updates the displayed sum according to the current game state.
     */
    void updateSum() {
        sum.setText(String.valueOf(Game.getInstance().getSum()));

    }

    /**
     * Attempts to play the selected card.
     *
     * @param card the card to be played
     * @return true if the card was successfully played, false otherwise
     */
    Boolean sentCard(String card) {
        return Game.getInstance().add(card);
    }

    /**
     * Initializes the game scene and prepares the user interface.
     *
     * This method creates the game state, loads the player's hand,
     * updates the last played card, and configures keyboard shortcuts.
     */
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

    /**
     * Displays the machine players that participate in the current match.
     *
     * @throws InvalidPlayersException if the number of players is invalid
     */
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

    /**
     * Disables the visual representation of an eliminated machine player.
     *
     * @param player the index of the eliminated player
     */
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

    /**
     * Selects a card from the player's hand.
     *
     * @param event mouse event generated when clicking a card
     */
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

    /**
     * Processes the selected card and performs the player's turn.
     *
     * If the card is valid, it is played and the turn changes.
     * If no valid card remains, the player is eliminated.
     *
     * @param mouseEvent mouse event that triggers the action
     */
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

    /**
     * Advances the game to the next turn.
     *
     * If the next participant is a machine, its turn is executed automatically.
     */
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

    /**
     * Executes the current machine player's turn.
     *
     * The machine waits a short period before selecting and playing
     * a card to simulate thinking time.
     */
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

    /**
     * Requests a card decision from the current machine player.
     *
     * @return the card selected by the machine, or null if no move is available
     */
    private String askMachine ()
    {
        AbstractPlayer player = Game.getInstance().getPlayer(turn);

        if (player instanceof Machine machine) {
            return machine.think(Game.getInstance().getSum());
        }

        return null;
    }

}


