package com.tech.challenge.fastfood.application.usecases.interactors;

import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.PedidoUseCase;
import com.tech.challenge.fastfood.application.usecases.exceptions.PedidoNaoEncontratoException;
import com.tech.challenge.fastfood.domain.Pedido;

import java.util.List;
import java.util.Objects;

public class PedidoInteractor implements PedidoUseCase {

    private final PedidoGateway pedidoGateway;

    public PedidoInteractor(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        return pedidoGateway.criarPedido(pedido);
    }

    public Pedido alterarSituacaoPedido(Long id, String situacao) {
        return pedidoGateway.alterarSituacaoPedido(id, situacao);
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoGateway.listarPedidos();
    }

    @Override
    public Pedido obterPedidoPorCPF(String cpf) {
        Pedido pedido = pedidoGateway.obterPedidoPorCPF(cpf);
        if (Objects.isNull(pedido)) {
            throw new PedidoNaoEncontratoException("Não foi possivel encontrar pedido nesse cpf: " + cpf);
        }
        return pedido;
    }

    @Override
    public Pedido checkoutPedido(Long id) {
        return pedidoGateway.checkoutPedido(id);
    }
}
