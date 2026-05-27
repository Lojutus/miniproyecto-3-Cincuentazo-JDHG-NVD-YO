package com.example.cincuentazo.model.Clases;

import com.example.cincuentazo.model.AbstractsClases.AbstractJuego;
import com.example.cincuentazo.model.Interfaces.IJuego;

public  class Juego extends AbstractJuego {
    private static final Juego INSTANCE = new Juego();


    private Juego() {}

    /**
     * This function helps to obtain the correct instance (the only instance).
     * @return the single instance
     */
    public static Juego getInstance() {
        return INSTANCE;
    } //singleton


    /**
     * Optional initializer hook for future use (e.g. resetting the singleton or reloading a puzzle).
     * Currently resolves the instance without changing board state.
     */
    public static void init() {
        Juego game = getInstance();

    }



}
