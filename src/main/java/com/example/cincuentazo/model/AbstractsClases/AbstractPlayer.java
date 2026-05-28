package com.example.cincuentazo.model.AbstractsClases;

import com.example.cincuentazo.model.Interfaces.IPlayer;

public abstract class AbstractPlayer implements IPlayer {
    String[] hand ={"", "", "", ""};
    public String[] Gethand(){
        return hand;
    }

}
