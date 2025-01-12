package com.tech.challenge.fastfood.application.exception.application.services.exceptions;

public class ProdutoInexistenteException extends RuntimeException {

    public ProdutoInexistenteException(String message) {
        super(message);
    }
}
