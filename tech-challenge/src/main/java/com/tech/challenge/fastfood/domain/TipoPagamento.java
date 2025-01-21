package com.tech.challenge.fastfood.domain;

public enum TipoPagamento {
    CREDITO, DEBITO, PIX;

//    CREDITO(new PagamentoCreditoStategy()),
//    DEBITO(new PagamentoDebitoStrategy()),
//    PIX(new PagamentoPixMercadoPagoStrategy());

//    private final PagamentoStrategy pagamentoStrategy;
//
//    TipoPagamento(PagamentoStrategy pagamentoStrategy) {
//        this.pagamentoStrategy = pagamentoStrategy;
//    }
//
//    public PagamentoStrategy getPagamentoStrategy() {
//        return pagamentoStrategy;
//    }
}
