package com.example.cincuentazo.model.AbstractsClases;

import com.example.cincuentazo.model.Clases.Deck;
import com.example.cincuentazo.model.Interfaces.IGame;
import com.example.cincuentazo.model.Clases.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame implements IGame {
    List<Player> jugadores = new ArrayList<>();
    int sum; // SUMA ACTUAL
    String lastCard; // Ultima carta
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
                    return true;
            }
            return false;
        }
        sum+=numTosum;
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

    Deck mazo;
    Boolean state;

}
