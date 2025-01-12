package com.tech.challenge.fastfood.application.usecases.interactors;

import com.tech.challenge.fastfood.application.usecases.FilaPedidosUseCase;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoFilaDTO;
import com.tech.challenge.fastfood.application.gateway.ClienteGateway;
import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;
import com.tech.challenge.fastfood.domain.SituacaoPedido;

import java.util.List;

public class FilaPedidoInteractor implements FilaPedidosUseCase {

    private final PedidoGateway pedidoGateway;

    public FilaPedidoInteractor(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public String notificaFila(Pedido pedido) {
        SituacaoPedido situacao = pedido.situacaoPedido();
        NotificacaoStrategy strategy = situacao.getStrategy();
        return strategy.notificar(pedido);
    }

    @Override
    public List<PedidoFilaDTO> listarPedidosNaFila() {
        List<Pedido> pedidosEmAndamento =  pedidoGateway.listarPedidos()
                .stream()
                .filter(pedido ->
                        !pedido.situacaoPedido().name().equals(SituacaoPedido.FINALIZADO.name()) &&
                        !pedido.situacaoPedido().name().equals(SituacaoPedido.EM_FILA_RETIRADA))
                .toList();

        return pedidosEmAndamento.stream().map(pedido ->
                        PedidoFilaDTO.builder()
                                .cpf(pedido.cliente().cpf())
                                .nomeCliente(pedido.cliente().nome())
                                .statusPedido(notificaFila(pedido))
                                .horarioIniciou(pedido.horarioInicio().toString())
                                .codigoPedido(pedido.codigoPedido())
                                .build())
                .toList();
    }

    @Override
    public List<PedidoFilaDTO> listarPedidosNaFilaRetirada() {
        List<Pedido> pedidosRetirada =  pedidoGateway.listarPedidos().stream().filter(
                pedido -> pedido.situacaoPedido().equals(SituacaoPedido.EM_FILA_RETIRADA)
        ).toList();

        return pedidosRetirada.stream().map(pedido ->
                        PedidoFilaDTO.builder()
                                .cpf(pedido.cliente().cpf())
                                .nomeCliente(pedido.cliente().nome())
                                .statusPedido(notificaFila(pedido))
                                .horarioIniciou(pedido.horarioInicio().toString())
                                .codigoPedido(pedido.codigoPedido())
                                .build())
                .toList();
    }

}
