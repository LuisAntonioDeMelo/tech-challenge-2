package com.tech.challenge.fastfood.application.usecases.pedidos.interactors;

import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.pedidos.ListarPedidosPorSituacaoUseCase;
import com.tech.challenge.fastfood.domain.Pedido;

import java.util.List;

public class ListarPedidosPorSituacaoInteractor implements ListarPedidosPorSituacaoUseCase {

    private PedidoGateway pedidoGateway;

    public ListarPedidosPorSituacaoInteractor(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public List<Pedido> listarPedidosPorSituacao(String situacao) {
        return pedidoGateway.listarPedidosPorSituacao(situacao);
    }
}
