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
    public void add(){
    }

    Deck mazo;
    Boolean state;

}
