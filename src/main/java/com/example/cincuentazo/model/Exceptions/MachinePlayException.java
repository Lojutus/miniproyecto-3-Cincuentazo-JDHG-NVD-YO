package com.example.cincuentazo.model.Exceptions;

public class MachinePlayException extends Exception{
    private final int turn;

    public MachinePlayException(int turn) {
        super("La maquina " + turn + " no puede jugar ninguna carta");
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }
}
