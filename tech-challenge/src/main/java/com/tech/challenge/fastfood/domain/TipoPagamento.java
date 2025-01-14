package com.tech.challenge.fastfood.domain;

import com.tech.challenge.fastfood.application.usecases.patterns.PagamentoStrategy;
import com.tech.challenge.fastfood.application.usecases.patterns.pagamentoStrategy.PagamentoCreditoStategy;
import com.tech.challenge.fastfood.application.usecases.patterns.pagamentoStrategy.PagamentoDebitoStrategy;
import com.tech.challenge.fastfood.application.usecases.patterns.pagamentoStrategy.PagamentoPixMercadoPagoStrategy;

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
