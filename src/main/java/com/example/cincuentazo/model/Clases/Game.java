package com.example.cincuentazo.model.Clases;

import com.example.cincuentazo.model.AbstractsClases.AbstractGame;

public  class Game extends AbstractGame {

    private static Game INSTANCE = new Game();


    private Game() {
        newPlayer();
    }

    /**
     * This function helps to obtain the correct instance (the only instance).
     * @return the single instance
     */
    public static Game getInstance() {
        return INSTANCE;
    } //singleton


    /**
     * Optional initializer hook for future use (e.g. resetting the singleton or reloading a puzzle).
     * Currently resolves the instance without changing board state.
     */
    public static void init() {
        Game game = getInstance();

    }
    public static void restartInstance(){
        INSTANCE = new Game();
    }



}
