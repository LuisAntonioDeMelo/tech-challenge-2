package com.tech.challenge.fastfood.application.usecases.produto.exceptions;

public class ProdutoInexistenteException extends RuntimeException {

    public ProdutoInexistenteException(String message) {
        super(message);
    }
}
