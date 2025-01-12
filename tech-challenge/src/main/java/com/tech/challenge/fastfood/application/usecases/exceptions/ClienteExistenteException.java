package com.tech.challenge.fastfood.application.usecases.exceptions;

public class ClienteExistenteException extends RuntimeException {

    public ClienteExistenteException(String message) {
        super(message);
    }
}
