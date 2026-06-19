package com.example.cincuentazo.model.Interfaces;

/**
 * Defines the basic behavior of a game participant.
 * @author José David Hurtado
 * @version 1.0
 */
public interface IPlayer {

    /**
     * Returns the player's current hand.
     *
     * @return array containing the player's cards
     */
    public String[] getHand();

    /**
     * Replaces a card in the player's hand.
     *
     * @param card new card to insert
     * @param Position position of the card to replace
     * @return true if the operation succeeds
     */
    public Boolean switchCard(String card , int Position);

}
