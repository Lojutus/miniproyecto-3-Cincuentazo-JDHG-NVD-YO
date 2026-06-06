package com.example.cincuentazo.controller;

import com.example.cincuentazo.controller.helpers.CardViewManager;
import com.example.cincuentazo.controller.helpers.HandSpritesHelper;
import com.example.cincuentazo.model.Clases.Game;
import com.example.cincuentazo.model.Clases.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class GameController
{
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

 String actualCard ;

 int maxPlayer;
 int turn = 0;

 ImageView selectedCard;
 public ImageView lastCardImage;


void updateSum(){
    sum.setText(String.valueOf(Game.getInstance().getSum()));

}
 Boolean sentCard(String card){
     return Game.getInstance().add(card);
 }
 @FXML
 public void initialize(){
     if(Game.getInstance().getPlayers() <=1){
         Game.getInstance().newPlayer();
         Game.getInstance().newPlayer();
         Game.getInstance().newPlayer();

     }
        Game.getInstance().initGame();
        spriteUpdaterHelper.updateCard(lastCardImage , Game.getInstance().getLastCard());
        maxPlayer = Game.getInstance().getPlayers();
        cartsSprites = new HandSpritesHelper(one, two , three , four);
        spriteUpdaterHelper.updateDeck(Game.getInstance().getPlayer(0) , cartsSprites);

        showActivePlayers();
 }

    private void showActivePlayers(){
        int players = Game.getInstance().getPlayers();
        if(players==1){ throw new RuntimeException("Solo hay un jugador"); }
        if(players>=2){ machine1.setDisable(false); machine1.setOpacity(1); }
        if( players >= 3){  machine2.setDisable(false); machine2.setOpacity(1);}
        if( players >= 4) { machine3.setDisable(false); machine3.setOpacity(1);}
        if(players>4){ throw new RuntimeException("Jugadores no validos"); }


    }
    private void disablePlayers(int player){
    if(player== 2) { machine1.setDisable(true); machine1.setOpacity(0.2); }
    if(player== 3) { machine2.setDisable(true); machine2.setOpacity(0.2); }
    if(player== 4) { machine3.setDisable(true); machine3.setOpacity(0.2); }

    }
 @FXML
    public void selectCard(MouseEvent event) {
         if(turn!=0) return;
         spriteUpdaterHelper.clean(selectedCard);
         selectedCard = (ImageView) event.getSource();
         spriteUpdaterHelper.clean(lastCardImage);
         spriteUpdaterHelper.clickEffect( selectedCard , lastCardImage , event);
         actualCard = selectedCard.getUserData().toString();
         updateSum();

    }
@FXML
public void sendInput(MouseEvent mouseEvent) {
        if(turn==0){
            if(sentCard(actualCard) ){

                spriteUpdaterHelper.updateCard(lastCardImage , actualCard);
                updateSum();
                Game.getInstance().changeHandCard(turn , actualCard);
                spriteUpdaterHelper.updateDeck(Game.getInstance().getPlayer(0) , cartsSprites);

                changeTurn();


            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("GAME OVER");
                alert.setHeaderText("Has perdido");
                alert.setContentText("Fin del juego");
                alert.showAndWait();
            }

        }

}
private void changeTurn(){
    spriteUpdaterHelper.clean(selectedCard);
    selectedCard = null;

    turn++;
    if(turn>Game.getInstance().getPlayers()){
        turn = 0;
        return;
    }

    machineTurn();



}
private void machineTurn(){

    Player player = Game.getInstance().getPlayer(turn-1);

    if(player.playing){
        /* if(!Game.getInstance().add(askMachine())){
            player.playing = false;
        }*/   //IMPLEMENTACIÓN PENDIENTE
        if(!Game.getInstance().add(player.getHand()[0])){
            player.playing = false;
            disablePlayers(turn);
            if(Game.getInstance().checkWin()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Felicidades");
                alert.setHeaderText("Has ganado");
                alert.setContentText("Fin del juego");
                alert.showAndWait();
            }
        } // Por ahora la máquina da la primera carta siempre
       // Game.getInstance().changeHandCard(turn-1 , 0);
    }

    updateSum();
    changeTurn();
}
private String askMachine(){
    //FUNCIÓN PENDIENTE DE IMPLEMENTACIÓN
    return "";
}


}
