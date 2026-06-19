package com.example.cincuentazo.model.Exceptions;

/**
 * Runtime exception used to wrap machine thread failures.
 * @author Yoskar Alomia Alvarado
 * @version 1.0
 */
public class MachineThreadException extends RuntimeException{
    public MachineThreadException(int turn, Throwable cause) {
        super("Error en el hilo de la maquina " + turn, cause);
    }
}
