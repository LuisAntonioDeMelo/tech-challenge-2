package com.tech.challenge.fastfood.core.application.services.exceptions;

public class ProdutoInexistenteException extends RuntimeException {

    public ProdutoInexistenteException(String message) {
        super(message);
    }
}
