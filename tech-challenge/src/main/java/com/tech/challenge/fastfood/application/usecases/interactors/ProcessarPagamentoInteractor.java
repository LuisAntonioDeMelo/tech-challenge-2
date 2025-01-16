package com.tech.challenge.fastfood.application.usecases.interactors;

import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.pagamento.ProcessarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.patterns.PagamentoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;

import java.util.Map;

public class ProcessarPagamentoInteractor implements ProcessarPagamentoUseCase {

    private final Map<String, PagamentoStrategy> strategies;
    private final PedidoGateway pedidoGateway;

    public ProcessarPagamentoInteractor(Map<String, PagamentoStrategy> strategies, PedidoGateway pedidoGateway) {
        this.strategies = strategies;
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public String processarPagamento(Long idPedido) {
        Pedido pedido = pedidoGateway.obterPedidoPorId(idPedido);
        PagamentoStrategy strategy = verificaPagamentoPedido(pedido);

        return strategy.processarPagamento(pedido);
    }

    private PagamentoStrategy verificaPagamentoPedido(Pedido pedido) {
        if(pedido.getPagamento() == null) {
            throw new IllegalArgumentException("Pedido não possui pagamento");
        }
        String tipoPagamento = pedido.getPagamento().getTipoPagamento().name();
        PagamentoStrategy strategy = strategies.get(tipoPagamento);
        if (strategy == null) {
            throw new IllegalArgumentException("Tipo de pagamento não suportado: " + tipoPagamento);
        }
        if(pedido.getPagamento().getValor() == null) {
            throw new IllegalArgumentException("Pedido não possui valor de pagamento");
        }

        return strategy;
    }


}
