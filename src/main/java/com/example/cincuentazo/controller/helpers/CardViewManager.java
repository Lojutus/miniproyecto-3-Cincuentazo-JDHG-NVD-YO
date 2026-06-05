package com.example.cincuentazo.controller.helpers;

import com.example.cincuentazo.model.Clases.Player;
import javafx.scene.image.Image;

import java.util.Objects;

public class CardViewManager {
    Boolean updateDeck(Player player , HandSpritesHelper handView){
        try {
            handView.setAllSlots(chargeImage(player.getHand()[0]), chargeImage(player.getHand()[1]) , chargeImage(player.getHand()[2]) , chargeImage(player.getHand()[3]));
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
    Image chargeImage(String cardName){
        return new Image(Objects.requireNonNull(CardViewManager.class.getResourceAsStream("/Cards/" + cardName + ".png")));
    }
}
