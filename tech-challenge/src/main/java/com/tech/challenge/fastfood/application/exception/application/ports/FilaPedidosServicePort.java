package com.tech.challenge.fastfood.application.exception.application.ports;

import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoFilaDTO;
import com.tech.challenge.fastfood.domain.Pedido;

import java.util.List;

public interface FilaPedidosServicePort {
    public String notificaFila(Pedido pedido);
    public List<PedidoFilaDTO> listarPedidosNaFila();
    public List<PedidoFilaDTO> listarPedidosNaFilaRetirada();
}
