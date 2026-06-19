package com.example.cincuentazo.controller.helpers;

import com.example.cincuentazo.model.AbstractsClases.AbstractPlayer;
import com.example.cincuentazo.model.Clases.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.Objects;

/**
 * Utility class responsible for managing card-related visual updates.
 *
 * This helper loads card images, updates card views, applies visual
 * effects, and manages the visibility of player hands.
 * @author José David Hurtado
 * @version 1.0
 */
public class CardViewManager {
    /**
     * Updates all card slots of a player's hand.
     *
     * @param player player whose hand will be displayed
     * @param handView visual representation of the hand
     */
    public void updateDeck(AbstractPlayer player , HandSpritesHelper handView){
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

    /**
     * Updates an image view with the specified card image.
     *
     * @param imageView target image view
     * @param card card identifier
     */
    public void updateCard(ImageView imageView, String card){
        imageView.setImage(chargeImage(card));
        imageView.setUserData(card);
    }

    /**
     * Loads the image associated with a card.
     *
     * @param cardName card identifier
     * @return loaded card image
     */
    public Image chargeImage(String cardName){
        return new Image(Objects.requireNonNull(CardViewManager.class.getResourceAsStream("/com/example/cincuentazo/Cards/" + cardName + ".png")));
    }

    /**
     * Applies the selection effect to a card.
     *
     * @param selectedCard selected card view
     * @param lastCardImage last played card view
     * @param event mouse event that triggered the selection
     */
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

    /**
     * Removes visual effects from a card view.
     *
     * @param card card view to clean
     */
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

    /**
     * Disables all cards in a hand and reduces their visibility.
     *
     * @param handView hand to disable
     */
    public void disableDeck(HandSpritesHelper handView) {

        handView.slot1.setDisable(true);
        handView.slot2.setDisable(true);
        handView.slot3.setDisable(true);
        handView.slot4.setDisable(true);

        handView.slot1.setOpacity(0.2);
        handView.slot2.setOpacity(0.2);
        handView.slot3.setOpacity(0.2);
        handView.slot4.setOpacity(0.2);
    }

    /**
     * Replaces all cards in a hand with the back image and disables them.
     *
     * @param handView hand to hide
     */
    public void hideDeck(HandSpritesHelper handView){

        Image back = chargeImage("back");

        handView.slot1.setImage(back);
        handView.slot2.setImage(back);
        handView.slot3.setImage(back);
        handView.slot4.setImage(back);

        disableDeck(handView);
    }
}
