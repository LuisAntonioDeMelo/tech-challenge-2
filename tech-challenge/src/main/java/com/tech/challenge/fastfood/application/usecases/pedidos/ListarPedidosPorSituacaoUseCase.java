package com.tech.challenge.fastfood.application.usecases.pedidos;

import com.tech.challenge.fastfood.domain.Pedido;

import java.util.List;

public interface ListarPedidosPorSituacaoUseCase {

    public List<Pedido> listarPedidosPorSituacao(String situacao);

}
