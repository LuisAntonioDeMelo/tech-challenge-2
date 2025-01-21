package com.tech.challenge.fastfood.application.usecases.cliente.exceptions;

public class InvalidCpfException extends RuntimeException{

    public InvalidCpfException(String msg) {
        super(msg);
    }
}
