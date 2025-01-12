package com.tech.challenge.fastfood.core.application.services.exceptions;

public class ClienteExistenteException extends RuntimeException {

    public ClienteExistenteException(String message) {
        super(message);
    }
}
