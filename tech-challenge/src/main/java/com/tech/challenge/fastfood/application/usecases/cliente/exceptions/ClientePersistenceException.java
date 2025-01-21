package com.tech.challenge.fastfood.application.usecases.cliente.exceptions;

public class ClientePersistenceException extends RuntimeException{
    public ClientePersistenceException(String mensagem){
        super(mensagem);
    }
}
