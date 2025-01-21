package com.tech.challenge.fastfood.application.usecases.pedidos.patterns;

import com.tech.challenge.fastfood.domain.Pedido;

public interface NotificacaoStrategy {
    String notificar(Pedido pedido);
}
