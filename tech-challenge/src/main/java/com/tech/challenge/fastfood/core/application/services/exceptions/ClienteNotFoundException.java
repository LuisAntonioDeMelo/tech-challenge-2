package com.tech.challenge.fastfood.core.application.services.exceptions;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String s) {
        super(s);
    }
}
