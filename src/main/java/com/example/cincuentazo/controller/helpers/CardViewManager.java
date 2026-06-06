package com.example.cincuentazo.controller.helpers;

import com.example.cincuentazo.model.Clases.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

public class CardViewManager {
    public void updateDeck(Player player , HandSpritesHelper handView){
        try {
            String[] hand = player.getHand();

            handView.setAllSlots(chargeImage(hand[0]), chargeImage(hand[1]) , chargeImage(hand[2]) , chargeImage(hand[3]));
            handView.slot1.setUserData(hand[0]);
            handView.slot2.setUserData(hand[1]);
            handView.slot3.setUserData(hand[2]);
            handView.slot4.setUserData(hand[3]);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
    public void updateCard(ImageView imageView, String card){
        imageView.setImage(chargeImage(card));
        imageView.setUserData(card);
    }
    public Image chargeImage(String cardName){
        return new Image(Objects.requireNonNull(CardViewManager.class.getResourceAsStream("/com/example/cincuentazo/Cards/" + cardName + ".png")));
    }
    public void clickEffect( ImageView selectedCard, ImageView lastCardImage , MouseEvent event){

        selectedCard.setStyle(
                "-fx-effect: dropshadow(gaussian, gold, 20, 0.5, 0, 0);"
        );
        selectedCard.setTranslateY(-15);
        selectedCard.setScaleX(1.1);
        selectedCard.setScaleY(1.1);
        lastCardImage.setStyle(
                "-fx-effect: dropshadow(gaussian, darkred, 40, 0.5, 0, 0);"
        );
    }
    public void clean(ImageView card){

        if(card!=null){
            card.setStyle(
                    ""
            );
            card.setTranslateY(0);
            card.setScaleX(1);
            card.setScaleY(1);

        }
    }
}
