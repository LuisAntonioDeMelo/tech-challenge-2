package com.tech.challenge.fastfood.core.application.ports;

import com.tech.challenge.fastfood.core.domain.Pedido;

import java.util.List;

public interface PedidoRepositoryPort {

    public List<Pedido> listarPedidos();
    public Pedido criarPedido(Pedido pedido);
    Pedido alterarSituacaoPedido(Long idPedido, String situacaoPedido);
    Pedido obterPedidoPorCPF(String cpf);
    Pedido checkoutPedido(Long idPedido);
}
