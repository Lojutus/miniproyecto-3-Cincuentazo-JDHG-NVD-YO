package com.example.cincuentazo.model.Exceptions;

/**
 * Exception thrown when a card cannot be legally played.
 * @author Yoskar Alomia Alvrado
 * @version 1.0
 */
public class InvalidCardException extends Exception {
    private final String card;

    /**
     * Creates a new invalid card exception.
     *
     * @param card invalid card identifier
     */
    public InvalidCardException(String card) {
        super("Carta invalida: " + card);
        this.card = card;
    }

    /**
     * Retrieves the card
     * @return the useless card
     */
    public String getCard() {
        return card;
    }
}
