package com.tech.challenge.fastfood.application.gateway;

import com.tech.challenge.fastfood.domain.Pedido;

import java.util.List;

public interface PedidoGateway {

    public List<Pedido> listarPedidos();
    public List<Pedido> listarPedidosPorSituacao(String situacao);
    public Pedido criarPedido(Pedido pedido);
    Pedido alterarSituacaoPedido(Long idPedido, String situacaoPedido);
    Pedido obterPedidoPorCPF(String cpf);
    Pedido checkoutPedido(Long idPedido);
    Pedido alterarPedido(Pedido pedido);
    Pedido obterPedidoPorId(Long idPedido);
    Pedido criarPagamentoPedido(Long id, String tipoPagamento);
}
