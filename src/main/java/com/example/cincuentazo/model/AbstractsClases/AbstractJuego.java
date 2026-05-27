package com.example.cincuentazo.model.AbstractsClases;

import com.example.cincuentazo.model.Clases.Mazo;
import com.example.cincuentazo.model.Interfaces.IJuego;
import com.example.cincuentazo.model.Clases.Jugador;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJuego implements IJuego {
    List<Jugador> jugadores = new ArrayList<>();
    int sum; // SUMA ACTUAL
    String lastCard; // Ultima carta
    public void add(){
    }

    Mazo mazo;
    Boolean state;

}
