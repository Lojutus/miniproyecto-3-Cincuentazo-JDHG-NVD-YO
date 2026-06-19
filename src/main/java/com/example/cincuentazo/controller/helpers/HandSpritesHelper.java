package com.example.cincuentazo.controller.helpers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Helper class that groups the four image slots representing a hand.
 *
 * It provides utility methods to update card images and manage
 * the visual representation of a player's hand.
 * @author José David Hurtado
 * @version 1.0
 */
public class HandSpritesHelper {

    public ImageView slot1;
    public ImageView slot2;
    public ImageView slot3;
    public ImageView slot4;

    /**
     * Creates a hand view using four image slots.
     *
     * @param slotR1 first card slot
     * @param slotR2 second card slot
     * @param slotR3 third card slot
     * @param slotR4 fourth card slot
     */
    public HandSpritesHelper(ImageView slotR1, ImageView slotR2, ImageView slotR3, ImageView slotR4){
        setAllSlots( slotR1 ,  slotR2 ,  slotR3,  slotR4);
    }

    /**
     * Assigns the image views used by the hand.
     *
     * @param slotR1 first slot
     * @param slotR2 second slot
     * @param slotR3 third slot
     * @param slotR4 fourth slot
     */
    public void  setAllSlots(ImageView slotR1 , ImageView slotR2 , ImageView slotR3, ImageView slotR4){
        slot1 = slotR1;
        slot2 = slotR2;
        slot3 = slotR3;
        slot4 = slotR4;
    }

    /**
     * Updates all card images displayed in the hand.
     *
     * @param slotR1 first card image
     * @param slotR2 second card image
     * @param slotR3 third card image
     * @param slotR4 fourth card image
     */
    public void  setAllSlots(Image slotR1 , Image slotR2 , Image slotR3, Image slotR4){
        slot1.setImage(slotR1);
        slot2.setImage(slotR2);
        slot3.setImage(slotR3);
        slot4.setImage(slotR4);
    }

    /**
     * Update the Image view for one slot
     *
     * @param slot1 first slot
     */
    public void setSlot1(ImageView slot1) {
        this.slot1 = slot1;
    }

    /**
     * Update the Image view for one slot
     *
     * @param slot2 second slot
     */
    public void setSlot2(ImageView slot2) {
        this.slot2 = slot2;
    }

    /**
     * Update the Image view for one slot
     *
     * @param slot3 third slot
     */
    public void setSlot3(ImageView slot3) {
        this.slot3 = slot3;
    }

    /**
     * Update the Image view for one slot
     *
     * @param slot4 forth slot
     */
    public void setSlot4(ImageView slot4) {
        this.slot4 = slot4;
    }
}
