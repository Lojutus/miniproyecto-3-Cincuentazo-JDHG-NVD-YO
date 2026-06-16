package com.example.cincuentazo.model.Exceptions;

public class MachineThreadException extends RuntimeException{
    public MachineThreadException(int turn, Throwable cause) {
        super("Error en el hilo de la maquina " + turn, cause);
    }
}
