package com.tech.challenge.fastfood.core.application.services.patterns.notificacaoStrategy;

import com.tech.challenge.fastfood.core.application.services.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.core.domain.Pedido;

public class PedidoEmMontagemStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Pedido pedido) {
        return "Pedido em está sendo montado";
    }
}
