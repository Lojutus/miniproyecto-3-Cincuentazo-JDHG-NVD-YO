package com.example.cincuentazo.model.AbstractsClases;

import com.example.cincuentazo.model.Clases.Deck;
import com.example.cincuentazo.model.Interfaces.IGame;
import com.example.cincuentazo.model.Clases.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame implements IGame {
    List<Player> Players = new ArrayList<>();
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
        if(sum + numTosum > 50 ){
            if( numTosum==10 && sum + 1 <= 50){
                    sum+=1;
                    lastCard = card;
                    return true;
            }
            return false;
        }
        sum+=numTosum;
        lastCard = card;
        return true;
    }
    public int check( char num){
        if(num>65){
            return -10;
        }
        else if(num == 65){
            return 10;
        } else if ( num-'0'==9) {
            return 0;
        }
        return num-'0';
    }
    public int getSum(){return sum;}
    public Boolean newPlayer(){
        if (state ==true) return false ;
        try {
            if( Players.size()<4){
                Players.add(new Player());
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;

    }
    public Player getPlayer(int index){
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
                    Player player = getPlayer(i);
                    if(!player.switchCard(deck.getCard(), j)){
                        throw new Exception("IT CANT BE SWITCH");
                    }
                }

            }
            return  true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
