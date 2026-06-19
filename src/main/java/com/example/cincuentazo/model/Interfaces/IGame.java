package com.example.cincuentazo.model.Interfaces;

import com.example.cincuentazo.model.AbstractsClases.AbstractPlayer;
/**
 * Defines the core operations of the game.
 * @author José David Hurtado
 * @version 1.0
 */
public interface IGame {
    /**
     * Attempts to play a card and update the current sum.
     *
     * @param card card to be played
     * @return true if the move is valid, false otherwise
     */
    Boolean add(String card);

    /**
     * Converts a card identifier into its corresponding game value.
     *
     * @param num card identifier
     * @return numerical value of the card
     */
    int check( char num);
    /**
     * Returns the current accumulated sum.
     *
     * @return current game sum
     */
    int getSum();


    /**
     * Retrieves a player by index.
     *
     * @param index player position
     * @return requested player
     */
    AbstractPlayer getPlayer(int index);

    /**
     * Returns the total number of players in the match.
     *
     * @return player count
     */
    int getPlayers();

    /**
     * Initializes the game and deals the starting hands.
     *
     * @return true if initialization succeeds
     */
    Boolean initGame();

    /**
     * Returns the last card played.
     *
     * @return last played card
     */
    String getLastCard();

    /**
     * Determines whether the game has a winner.
     *
     * @return true if only one active player remains
     */
    Boolean checkWin();

    /**
     * Replaces a card at the specified position in a player's hand.
     *
     * @param playerIndex target player index
     * @param position card position to replace
     * @return true if the replacement succeeds
     */
    Boolean changeHandCard(int playerIndex , int position);

    /**
     * Replaces a played card with a new one drawn from the deck.
     *
     * @param playerIndex target player index
     * @param card played card
     * @return true if the replacement succeeds
     */
    Boolean changeHandCard(int playerIndex , String card);

    /**
     * Eliminates a player from the current match.
     *
     * @param playerIndex index of the eliminated player
     */
    void playerLose(int playerIndex);
}
