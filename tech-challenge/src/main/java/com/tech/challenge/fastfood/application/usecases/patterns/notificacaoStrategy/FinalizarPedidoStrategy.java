package com.tech.challenge.fastfood.application.usecases.patterns.notificacaoStrategy;

import com.tech.challenge.fastfood.application.usecases.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;

public class FinalizarPedidoStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Pedido pedido) {
        return "Finalizar Pedido";
    }
}
