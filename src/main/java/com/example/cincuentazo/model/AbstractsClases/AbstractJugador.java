package com.example.cincuentazo.model.AbstractsClases;

import com.example.cincuentazo.model.Interfaces.IJugador;

public abstract class AbstractJugador implements IJugador {
    String[] hand ={"", "", "", ""};
    public String[] Gethand(){
        return hand;
    }

}
