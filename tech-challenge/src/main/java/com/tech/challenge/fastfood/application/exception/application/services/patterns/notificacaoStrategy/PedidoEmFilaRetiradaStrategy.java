package com.tech.challenge.fastfood.application.exception.application.services.patterns.notificacaoStrategy;

import com.tech.challenge.fastfood.application.exception.application.services.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;

public class PedidoEmFilaRetiradaStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Pedido pedido) {
        return "Pedido está esperando ser retirado!";
    }
}
