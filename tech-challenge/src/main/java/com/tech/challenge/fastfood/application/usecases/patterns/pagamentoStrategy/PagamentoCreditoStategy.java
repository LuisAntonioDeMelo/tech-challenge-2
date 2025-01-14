package com.tech.challenge.fastfood.application.usecases.patterns.pagamentoStrategy;

import com.tech.challenge.fastfood.application.usecases.patterns.PagamentoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;
import org.springframework.beans.factory.annotation.Qualifier;


@Qualifier("credito")
public class PagamentoCreditoStategy implements PagamentoStrategy {

    @Override
    public String processarPagamento(Pedido pedido) {
        return "credito";
    }


}
