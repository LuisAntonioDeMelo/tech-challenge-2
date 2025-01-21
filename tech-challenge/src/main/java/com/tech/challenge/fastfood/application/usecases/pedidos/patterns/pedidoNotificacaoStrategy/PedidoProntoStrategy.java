package com.tech.challenge.fastfood.application.usecases.pedidos.patterns.pedidoNotificacaoStrategy;

import com.tech.challenge.fastfood.application.usecases.pedidos.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;

public class PedidoProntoStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Pedido pedido) {
        return "Pedido está pronto para ser retirado!";
    }
}
