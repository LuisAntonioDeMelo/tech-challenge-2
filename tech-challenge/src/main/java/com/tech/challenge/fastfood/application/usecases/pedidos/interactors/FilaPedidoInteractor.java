package com.tech.challenge.fastfood.application.usecases.pedidos.interactors;

import com.tech.challenge.fastfood.application.usecases.pedidos.FilaPedidosUseCase;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoFilaDTO;
import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.pedidos.patterns.NotificacaoStrategy;
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
        SituacaoPedido situacao = pedido.getSituacaoPedido();
        NotificacaoStrategy strategy = situacao.getStrategy();
        return strategy.notificar(pedido);
    }

    @Override
    public List<PedidoFilaDTO> listarPedidosNaFila() {
        List<Pedido> pedidosEmAndamento =  pedidoGateway.listarPedidos()
                .stream()
                .filter(pedido ->
                        !pedido.getSituacaoPedido().name().equals(SituacaoPedido.FINALIZADO.name()) &&
                        !pedido.getSituacaoPedido().name().equals(SituacaoPedido.PRONTO.name()))
                .toList();

        return pedidosEmAndamento.stream().map(pedido ->
                        PedidoFilaDTO.builder()
                                .cpf(pedido.getCliente().getCpf())
                                .nomeCliente(pedido.getCliente().getNome())
                                .statusPedido(notificaFila(pedido))
                                .horarioIniciou(pedido.getHorarioInicio().toString())
                                .codigoPedido(pedido.getCodigoPedido())
                                .build())
                .toList();
    }

    @Override
    public List<PedidoFilaDTO> listarPedidosProntos() {
        List<Pedido> pedidosRetirada =  pedidoGateway.listarPedidos().stream().filter(
                pedido -> pedido.getSituacaoPedido().equals(SituacaoPedido.PRONTO)
        ).toList();

        return pedidosRetirada.stream().map(pedido ->
                        PedidoFilaDTO.builder()
                                .cpf(pedido.getCliente().getCpf())
                                .nomeCliente(pedido.getCliente().getNome())
                                .statusPedido(notificaFila(pedido))
                                .horarioIniciou(pedido.getHorarioInicio().toString())
                                .codigoPedido(pedido.getCodigoPedido())
                                .build())
                .toList();
    }

}
