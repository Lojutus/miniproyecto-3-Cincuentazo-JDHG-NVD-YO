package com.example.cincuentazo.model.Exceptions;
/**
 * Exception thrown when an invalid number of players is detected.
 * @author Yoskar Alomia Alvarado
 * @version 1.0
 */
public class InvalidPlayersException extends RuntimeException{
    public InvalidPlayersException(int players){
        super("Numero invalido de jugadores" + players);
    }
}
