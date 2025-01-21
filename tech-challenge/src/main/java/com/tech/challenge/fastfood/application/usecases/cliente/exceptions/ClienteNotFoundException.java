package com.tech.challenge.fastfood.application.usecases.cliente.exceptions;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String s) {
        super(s);
    }
}
