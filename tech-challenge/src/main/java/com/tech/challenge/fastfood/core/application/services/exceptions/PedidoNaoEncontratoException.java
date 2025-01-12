package com.tech.challenge.fastfood.core.application.services.exceptions;

public class PedidoNaoEncontratoException extends RuntimeException {
    public PedidoNaoEncontratoException(String message) {
        super(message);
    }
}
