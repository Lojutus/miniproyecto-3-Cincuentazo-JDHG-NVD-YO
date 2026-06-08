package com.example.cincuentazo.model.AbstractsClases;

import com.example.cincuentazo.model.Interfaces.IPlayer;

public abstract class AbstractPlayer implements IPlayer {

    String[] hand ={"", "", "", ""};
    public Boolean playing = true;

    public String[] getHand(){
        return hand;
    }

    public Boolean switchCard(String card, int position) {
        if(card.length()>3 || position>3 || position<0) return false;
        try {
             hand[position] = card;

        } catch (Exception e) {

             throw new RuntimeException(e);
        }
         return true;


    }




}
