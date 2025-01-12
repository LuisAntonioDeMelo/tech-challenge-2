package com.tech.challenge.fastfood.application.exception.application.ports;

import com.tech.challenge.fastfood.domain.Pedido;

import java.util.List;

public interface PedidoServicePort {
    Pedido criarPedido(Pedido pedido);
    Pedido alterarSituacaoPedido(Long id, String situacaoPedido);
    public List<Pedido> listarPedidos();
    Pedido obterPedidoPorCPF(String cpf);

    Pedido checkoutPedido(Long id);
}
