package com.example.cincuentazo.model.AbstractsClases;

import com.example.cincuentazo.model.Clases.Deck;
import com.example.cincuentazo.model.Interfaces.IGame;
import com.example.cincuentazo.model.Clases.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractGame implements IGame {
    List<AbstractPlayer> Players = new ArrayList<>();
    int sum; // SUMA ACTUAL
    String lastCard; // Ultima carta
    public Deck deck = new Deck();
    Boolean state =false;
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
    public int getSum(){return sum;}
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
    public AbstractPlayer getPlayer(int index){
        return Players.get(index);
    }
    public int getPlayers(){
        return Players.size();
    }
    public Boolean initGame(){
        if(state) return false;
        state =true;
        try {
            for (int i = 0; i < Players.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    AbstractPlayer player = getPlayer(i);
                    if(!player.switchCard(deck.getCard(), j)){
                        throw new Exception("IT CANT BE SWITCH");
                    }
                }

            }
            lastCard = deck.getCard();
            add(lastCard);
            return  true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public String getLastCard(){return lastCard;}
    public Boolean checkWin(){
        if(!state ) return false;
        for (int i = 1; i < getPlayers()-1; i++) {
            if(getPlayer(i).playing){
                return false;
            }
        }
        return true;
    }
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
public Boolean changeHandCard(int playerIndex , int position){
    return getPlayer(playerIndex).switchCard(deck.getCard(), position);
}
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



