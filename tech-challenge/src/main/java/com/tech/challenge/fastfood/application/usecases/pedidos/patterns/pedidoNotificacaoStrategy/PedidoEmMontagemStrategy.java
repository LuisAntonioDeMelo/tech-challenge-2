package com.tech.challenge.fastfood.application.usecases.pedidos.patterns.pedidoNotificacaoStrategy;

import com.tech.challenge.fastfood.application.usecases.pedidos.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.domain.Pedido;

public class PedidoEmMontagemStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Pedido pedido) {
        return "Pedido em está sendo montado";
    }
}
