package com.tech.challenge.fastfood.application.exception.application.services;

import com.tech.challenge.fastfood.application.exception.application.ports.PedidoRepositoryPort;
import com.tech.challenge.fastfood.application.exception.application.ports.PedidoServicePort;
import com.tech.challenge.fastfood.application.exception.application.services.exceptions.PedidoNaoEncontratoException;
import com.tech.challenge.fastfood.domain.Pedido;

import java.util.List;
import java.util.Objects;

public class PedidoService implements PedidoServicePort {

    private final PedidoRepositoryPort pedidoRepositoryPort;

    public PedidoService(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        return pedidoRepositoryPort.criarPedido(pedido);
    }

    public Pedido alterarSituacaoPedido(Long id, String situacao) {
        return pedidoRepositoryPort.alterarSituacaoPedido(id, situacao);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepositoryPort.listarPedidos();
    }

    @Override
    public Pedido obterPedidoPorCPF(String cpf) {
        Pedido pedido = pedidoRepositoryPort.obterPedidoPorCPF(cpf);
        if (Objects.isNull(pedido)) {
            throw new PedidoNaoEncontratoException("Não foi possivel encontrar pedido nesse cpf: " + cpf);
        }
        return pedido;
    }

    @Override
    public Pedido checkoutPedido(Long id) {
        return pedidoRepositoryPort.checkoutPedido(id);
    }
}
