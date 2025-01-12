package com.tech.challenge.fastfood.application.usecases;

import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoFilaDTO;
import com.tech.challenge.fastfood.domain.Pedido;

import java.util.List;

public interface FilaPedidosUseCase {
    public String notificaFila(Pedido pedido);
    public List<PedidoFilaDTO> listarPedidosNaFila();
    public List<PedidoFilaDTO> listarPedidosNaFilaRetirada();
}
