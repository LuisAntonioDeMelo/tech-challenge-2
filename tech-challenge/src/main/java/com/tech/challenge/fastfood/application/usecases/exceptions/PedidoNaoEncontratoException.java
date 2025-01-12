package com.tech.challenge.fastfood.application.usecases.exceptions;

public class PedidoNaoEncontratoException extends RuntimeException {
    public PedidoNaoEncontratoException(String message) {
        super(message);
    }
}
