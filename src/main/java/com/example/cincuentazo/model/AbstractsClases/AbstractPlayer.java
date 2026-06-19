package com.example.cincuentazo.model.AbstractsClases;

import com.example.cincuentazo.model.Interfaces.IPlayer;

/**
 * Base implementation of a game participant.
 *
 * Stores the player's hand and active state.
 * @author José David Hurtado
 * @version 1.0
 */
public abstract class AbstractPlayer implements IPlayer {

    String[] hand ={"", "", "", ""};
    public Boolean playing = true;

    /**
     * Returns the player's current hand.
     *
     * @return array containing the player's cards
     */
    public String[] getHand(){
        return hand;
    }

    /**
     * Replaces a card in the player's hand.
     *
     * @param card new card
     * @param position target position in the hand
     * @return true if the card was replaced successfully
     */
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
