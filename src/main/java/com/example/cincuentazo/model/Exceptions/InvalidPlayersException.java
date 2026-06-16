package com.example.cincuentazo.model.Exceptions;

public class InvalidPlayersException extends RuntimeException{
    public InvalidPlayersException(int players){
        super("Numero invalido de jugadores" + players);
    }
}
