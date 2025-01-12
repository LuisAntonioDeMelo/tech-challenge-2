package com.tech.challenge.fastfood.application.usecases.exceptions;

public class ClientePersistenceException extends RuntimeException{
    public ClientePersistenceException(String mensagem){
        super(mensagem);
    }
}
