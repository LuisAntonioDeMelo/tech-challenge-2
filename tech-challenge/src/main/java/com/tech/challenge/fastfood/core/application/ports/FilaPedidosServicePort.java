package com.tech.challenge.fastfood.core.application.ports;

import com.tech.challenge.fastfood.adapter.driver.api.dtos.PedidoFilaDTO;
import com.tech.challenge.fastfood.core.domain.Pedido;

import java.util.List;

public interface FilaPedidosServicePort {
    public String notificaFila(Pedido pedido);
    public List<PedidoFilaDTO> listarPedidosNaFila();
    public List<PedidoFilaDTO> listarPedidosNaFilaRetirada();
}
