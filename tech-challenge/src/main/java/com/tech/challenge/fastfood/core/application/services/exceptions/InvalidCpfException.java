package com.tech.challenge.fastfood.core.application.services.exceptions;

public class InvalidCpfException extends RuntimeException{

    public InvalidCpfException(String msg) {
        super(msg);
    }
}
