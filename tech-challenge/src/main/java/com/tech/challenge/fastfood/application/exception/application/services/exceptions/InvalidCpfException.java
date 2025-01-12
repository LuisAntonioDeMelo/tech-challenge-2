package com.tech.challenge.fastfood.application.exception.application.services.exceptions;

public class InvalidCpfException extends RuntimeException{

    public InvalidCpfException(String msg) {
        super(msg);
    }
}
