package com.tech.challenge.fastfood.application.exception.application.services.exceptions;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String s) {
        super(s);
    }
}
