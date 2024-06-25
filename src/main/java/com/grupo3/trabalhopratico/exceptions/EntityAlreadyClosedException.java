package com.grupo3.trabalhopratico.exceptions;

public class EntityAlreadyClosedException extends RuntimeException {
    public EntityAlreadyClosedException(String message) {
        super(message);
    }
}
