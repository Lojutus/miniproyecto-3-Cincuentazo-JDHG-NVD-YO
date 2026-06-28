package com.example.cincuentazo.model.AbstractsClasses;

import com.example.cincuentazo.model.Classes.Deck;
import com.example.cincuentazo.model.Exceptions.InvalidCardException;
import com.example.cincuentazo.model.Interfaces.IGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract implementation of the game logic.
 *
 * This class manages players, turns, cards, score calculation,
 * and the overall state of the match.
 * @author José David Hurtado
 * @version 1.0
 */
public abstract class AbstractGame implements IGame {
    List<AbstractPlayer> Players = new ArrayList<>();
    int sum; // SUMA ACTUAL
    String lastCard; // Ultima carta
    public Deck deck = new Deck();
    Boolean state =false;

    /**
     * Attempts to add a card value to the current sum.
     *
     * @param card card to be played
     * @return true if the move is valid, false otherwise
     */
    public Boolean add(String card){
        int numTosum= 0;
        try {

            char num = card.charAt(0) ;
            numTosum =check(num);

        }catch (Exception e){
            return false;
        }
        if(sum + numTosum > 50 && numTosum!=11){
            if( numTosum==10 && sum + 1 <= 50){
                    sum+=1;
                    lastCard = card;
                    return true;
            }
            return false;
        }
        if (numTosum ==11) numTosum =10;// if is eleven means that the orignal numtosum was 10
        if(sum + numTosum > 50) return false;
        sum+=numTosum;
        lastCard = card;
        return true;
    }

    /**
     * Converts a card identifier into its numeric game value.
     *
     * @param num first character of the card identifier
     * @return the value associated with the card
     */
    public int check( char num){
        if(num>65){
            return -10;
        }
        else if(num==65){
            return 10;
        }
        else if( num-'0'==1){
            return 11; // Ten is busy whit "A" so we use eleven to send a 10
        } else if ( num-'0'==9) {
            return 0;
        }
        return num-'0';
    }

    /**
     * Returns the current accumulated sum.
     *
     * @return current sum
     */
    public int getSum(){return sum;}

    /**
     * Adds a player to the match before the game starts.
     *
     * @param player player to add
     * @return true if the player was added successfully
     */
    public Boolean newPlayer(AbstractPlayer player){
        if (state ==true) return false ;
        try {
            if( Players.size()<4){
                Players.add(player);
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;

    }

    /**
     * Retrieves a player by index.
     *
     * @param index player position
     * @return requested player
     */
    public AbstractPlayer getPlayer(int index){
        return Players.get(index);
    }

    /**
     * Returns the number of registered players.
     *
     * @return total player count
     */
    public int getPlayers(){
        return Players.size();
    }

    /**
     * Starts the game and deals the initial cards.
     *
     * @return true if the game was initialized successfully
     */
    public Boolean initGame(){
        if(state) return false;
        state =true;
        try {
            for (int i = 0; i < Players.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    AbstractPlayer player = getPlayer(i);
                    if(!player.switchCard(deck.getCard(), j)){
                        throw new InvalidCardException(deck.getCard());
                    }
                }

            }
            lastCard = deck.getCard();
            add(lastCard);
            return  true;
        } catch (InvalidCardException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Returns the last card played in the match.
     *
     * @return the identifier of the last played card
     */
    public String getLastCard(){return lastCard;}

    /**
     * Determines whether the match has a winner.
     *
     * A winner exists when only one active player remains in the game.
     *
     * @return true if the game has been won, false otherwise
     */
    public Boolean checkWin(){
        int activePlayers = 0;

        for (int i = 0; i < getPlayers(); i++) {
            if (getPlayer(i).playing) {
                activePlayers++;
            }
        }

        return activePlayers == 1;
    }
    /**
     * Retrieves the index of the remaining active player.
     *
     * @return the winner's index, or -1 if no winner exists
     */
    public int getWinnerIndex() {

        for (int i = 0; i < getPlayers(); i++) {
            if (getPlayer(i).playing) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Determines whether the human player has any valid moves remaining.
     *
     * A player loses when none of the cards in their hand can be legally played.
     *
     * @return true if the player has lost, false otherwise
     */
    public Boolean checkLose(){
        if(!state ) return false;

        for (int i = 0; i < 4; i++) {
            int numTosum= 0;
            String card = getPlayer(0).getHand()[i];

                char num = card.charAt(0) ;
                numTosum =check(num);


            if(sum + numTosum > 50 && numTosum!=11 && !(numTosum==10 && sum + 1 <= 50)){
               continue;
            }
            if (numTosum ==11) numTosum =10;// if is eleven means that the original numtosum was 10
            if(sum + numTosum > 50) continue;
            return false;
        }
        return true;
    }

    /**
     * Replaces a played card with a new card drawn from the deck.
     *
     * The played card is returned to the deck and a replacement
     * card is assigned to the provided player.
     *
     * @param playerIndex player whose hand will be updated
     * @param position card that was played
     */
    public Boolean changeHandCard(int playerIndex , int position){
        return getPlayer(playerIndex).switchCard(deck.getCard(), position);
    }

    /**
     * Replaces a card in a player's hand with a new one drawn from the deck.
     *
     * The played card is returned to the deck and a replacement card
     * is assigned to the specified player.
     *
     * @param playerIndex index of the player
     * @param card card that was played
     */
    public Boolean changeHandCard(int playerIndex , String card){
        for (int i = 0; i < 4; i++) {
            if (Objects.equals(getPlayer(playerIndex).getHand()[i], card)) {
                return getPlayer(playerIndex).switchCard(deck.getCard(), i);
            }
        }
           return false;
        }
        public void playerLose(int playerIndex){
            for (int i = 0; i < 4; i++) {
                deck.addCard(getPlayer(playerIndex).getHand()[i]);
            }
            getPlayer(playerIndex).playing = false;
        }

}



