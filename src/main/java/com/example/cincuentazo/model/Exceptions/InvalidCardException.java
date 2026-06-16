package com.example.cincuentazo.model.Exceptions;

public class InvalidCardException extends Exception {
    private final String card;

    public InvalidCardException(String card) {
        super("Carta invalida: " + card);
        this.card = card;
    }

    public String getCard() {
        return card;
    }
}
