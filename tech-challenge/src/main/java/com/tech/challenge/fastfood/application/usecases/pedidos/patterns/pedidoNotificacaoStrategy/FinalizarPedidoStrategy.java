package com.tech.challenge.fastfood.application.usecases.pedidos.patterns.pedidoNotificacaoStrategy;

import com.tech.challenge.fastfood.application.usecases.pedidos.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;

public class FinalizarPedidoStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Pedido pedido) {
        return "Finalizar Pedido";
    }
}
