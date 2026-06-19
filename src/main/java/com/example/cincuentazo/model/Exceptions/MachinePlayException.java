package com.example.cincuentazo.model.Exceptions;

/**
 * Exception thrown when a machine cannot perform a valid move.
 * @author Yoskar Alomia Alvarado
 * @version 1.0
 */

public class MachinePlayException extends Exception{
    private final int turn;

    /**
     * Creates a new machine play exception .
     *
     * @param turn of the machine
     */
    public MachinePlayException(int turn) {
        super("La maquina " + turn + " no puede jugar ninguna carta");
        this.turn = turn;
    }

    /**
     * Retrieves the turn
     * @return the turn
     */
    public int getTurn() {
        return turn;
    }
}
