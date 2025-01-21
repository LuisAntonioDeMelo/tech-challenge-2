package com.tech.challenge.fastfood.application.usecases.pagamento.pagamentoStrategy;

import com.tech.challenge.fastfood.domain.Pedido;
import org.springframework.stereotype.Component;


public interface PagamentoStrategy {
    public String processarPagamento(Pedido pedido);
}
