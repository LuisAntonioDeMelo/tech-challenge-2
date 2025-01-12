package com.tech.challenge.fastfood.application.exception.application.services;

import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoFilaDTO;
import com.tech.challenge.fastfood.application.exception.application.ports.ClienteRepositoryPort;
import com.tech.challenge.fastfood.application.exception.application.ports.FilaPedidosServicePort;
import com.tech.challenge.fastfood.application.exception.application.ports.PedidoRepositoryPort;
import com.tech.challenge.fastfood.application.exception.application.services.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;
import com.tech.challenge.fastfood.domain.SituacaoPedido;

import java.util.List;

public class FilaPedidosService implements FilaPedidosServicePort {

    private final PedidoRepositoryPort pedidoRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;

    public FilaPedidosService(PedidoRepositoryPort pedidoRepositoryPort, ClienteRepositoryPort clienteRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public String notificaFila(Pedido pedido) {
        SituacaoPedido situacao = pedido.getSituacaoPedido();
        NotificacaoStrategy strategy = situacao.getStrategy();
        return strategy.notificar(pedido);
    }

    @Override
    public List<PedidoFilaDTO> listarPedidosNaFila() {
        List<Pedido> pedidosEmAndamento =  pedidoRepositoryPort.listarPedidos()
                .stream()
                .filter(pedido ->
                        !pedido.getSituacaoPedido().name().equals(SituacaoPedido.FINALIZADO.name()) &&
                        !pedido.getSituacaoPedido().name().equals(SituacaoPedido.EM_FILA_RETIRADA))
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
    public List<PedidoFilaDTO> listarPedidosNaFilaRetirada() {
        List<Pedido> pedidosRetirada =  pedidoRepositoryPort.listarPedidos().stream().filter(
                pedido -> pedido.getSituacaoPedido().equals(SituacaoPedido.EM_FILA_RETIRADA)
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
