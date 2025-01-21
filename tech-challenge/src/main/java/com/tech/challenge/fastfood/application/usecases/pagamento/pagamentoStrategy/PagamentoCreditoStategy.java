package com.tech.challenge.fastfood.application.usecases.pagamento.pagamentoStrategy;

import com.tech.challenge.fastfood.domain.Pedido;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component("credito")
public class PagamentoCreditoStategy implements PagamentoStrategy {

    @Override
    public String processarPagamento(Pedido pedido) {
        return "credito";
    }


}
