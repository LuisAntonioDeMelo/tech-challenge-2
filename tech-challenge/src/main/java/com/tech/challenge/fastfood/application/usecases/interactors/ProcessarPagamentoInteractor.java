package com.tech.challenge.fastfood.application.usecases.interactors;

import com.tech.challenge.fastfood.application.usecases.ProcessarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.patterns.PagamentoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;

import java.util.Map;

public class ProcessarPagamentoInteractor implements ProcessarPagamentoUseCase {

    private final Map<String, PagamentoStrategy> strategies;

    public ProcessarPagamentoInteractor(Map<String, PagamentoStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public String processarPagamento(Long idPedido) {
        Pedido pedido = new Pedido();
        String tipoPagamento = pedido.getPagamento().getTipoPagamento().name();
        PagamentoStrategy strategy = strategies.get(tipoPagamento);

        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de pagamento não suportado: " + tipoPagamento);
        }
        return strategy.processarPagamento(pedido);
    }


}
