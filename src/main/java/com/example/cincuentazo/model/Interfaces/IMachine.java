package com.example.cincuentazo.model.Interfaces;

/**
 * Defines decision-making behavior for machine players.
 * @author José David Hurtado
 * @version 1.0
 */
public interface IMachine {

    /**
     * Selects a move according to the current game state.
     *
     * @param currentSum current accumulated sum
     * @return selected card
     */
    public String think( int currentSum);
}
