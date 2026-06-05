package com.example.cincuentazo.controller;

import com.example.cincuentazo.controller.helpers.CardViewManager;
import com.example.cincuentazo.controller.helpers.HandSpritesHelper;
import com.example.cincuentazo.model.Clases.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController
{
    public ImageView two;
    public ImageView one;
    public ImageView three;
    public ImageView four;
    public Label sum;
    HandSpritesHelper cartsSprites;
    CardViewManager spriteUpdaterHelper = new CardViewManager();
 String actualCard ;
 String lastCard ;
 ImageView selectedCard;
 public ImageView lastCardImage;
int maxPlayer;
int turn = 0;
void updateSum(){
    sum.setText(String.valueOf(Game.getInstance().getSum()));

}
 Boolean sentCard(String card){
     return Game.getInstance().add(card);
 }
 @FXML
 public void initialize(){
        Game.getInstance().initGame();
     spriteUpdaterHelper.updateCard(lastCardImage , Game.getInstance().getLastCard());
        maxPlayer = Game.getInstance().getPlayers();
        cartsSprites = new HandSpritesHelper(one, two , three , four);
        spriteUpdaterHelper.updateDeck(Game.getInstance().getPlayer(0) , cartsSprites);
 }
 @FXML
    public void selectCard(MouseEvent event) {
     spriteUpdaterHelper.clean(selectedCard);
     selectedCard = (ImageView) event.getSource();
     spriteUpdaterHelper.clean(lastCardImage);
    spriteUpdaterHelper.clickEffect( selectedCard , lastCardImage , event);
    actualCard = selectedCard.getUserData().toString();
     updateSum();

    }
@FXML
public void sendInput(MouseEvent mouseEvent) {
        if(sentCard(actualCard) && turn==0){
            spriteUpdaterHelper.updateCard(lastCardImage , actualCard);
            updateSum();
            turn++;
        }

}
private void askMachine(){
    //FUNCION PENDIENTE DE IMPLEMENTACION
}


}
