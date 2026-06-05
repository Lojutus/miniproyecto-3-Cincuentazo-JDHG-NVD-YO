package com.example.cincuentazo.controller.helpers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HandSpritesHelper {

    public ImageView slot1;
    public ImageView slot2;
    public ImageView slot3;
    public ImageView slot4;
    HandSpritesHelper(ImageView slotR1 , ImageView slotR2 , ImageView slotR3, ImageView slotR4){
        setAllSlots( slotR1 ,  slotR2 ,  slotR3,  slotR4);
    }

    public void  setAllSlots(ImageView slotR1 , ImageView slotR2 , ImageView slotR3, ImageView slotR4){
        slot1 = slotR1;
        slot2 = slotR2;
        slot3 = slotR3;
        slot4 = slotR4;
    }
    public void  setAllSlots(Image slotR1 , Image slotR2 , Image slotR3, Image slotR4){
        slot1.setImage(slotR1);
        slot2.setImage(slotR1);
        slot3.setImage(slotR1);
        slot4.setImage(slotR1);
    }

    public void setSlot1(ImageView slot1) {
        this.slot1 = slot1;
    }

    public void setSlot2(ImageView slot2) {
        this.slot2 = slot2;
    }

    public void setSlot3(ImageView slot3) {
        this.slot3 = slot3;
    }

    public void setSlot4(ImageView slot4) {
        this.slot4 = slot4;
    }
}
