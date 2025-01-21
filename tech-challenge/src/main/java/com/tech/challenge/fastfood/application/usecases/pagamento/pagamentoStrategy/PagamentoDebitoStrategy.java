package com.tech.challenge.fastfood.application.usecases.pagamento.pagamentoStrategy;

import com.tech.challenge.fastfood.domain.Pedido;
import org.springframework.beans.factory.annotation.Qualifier;


@Qualifier("debito")
public class PagamentoDebitoStrategy implements PagamentoStrategy {
    @Override
    public String processarPagamento(Pedido pedido) {
        return "debito";
    }
}
