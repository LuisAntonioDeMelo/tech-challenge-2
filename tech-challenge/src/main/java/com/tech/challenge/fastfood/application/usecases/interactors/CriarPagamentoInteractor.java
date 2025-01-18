package com.tech.challenge.fastfood.application.usecases.interactors;

import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.pagamento.CriarPagamentoUseCase;
import com.tech.challenge.fastfood.domain.Pedido;

public class CriarPagamentoInteractor implements CriarPagamentoUseCase {

    private PedidoGateway pedidoGateway;

    public CriarPagamentoInteractor(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido criarPagamentoPedido(Long id, String tipoPagamento) {
        return pedidoGateway.criarPagamentoPedido(id, tipoPagamento);
    }
}
