package com.tech.challenge.fastfood.application.exception;

public class ClientePersistenceException extends RuntimeException{
    public ClientePersistenceException(String mensagem){
        super(mensagem);
    }
}
