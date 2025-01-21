package com.tech.challenge.fastfood.application.usecases.cliente.exceptions;

public class ClienteExistenteException extends RuntimeException {

    public ClienteExistenteException(String message) {
        super(message);
    }
}
