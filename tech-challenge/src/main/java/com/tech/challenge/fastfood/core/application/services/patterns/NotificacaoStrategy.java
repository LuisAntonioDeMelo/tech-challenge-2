package com.tech.challenge.fastfood.core.application.services.patterns;

import com.tech.challenge.fastfood.core.domain.Pedido;

public interface NotificacaoStrategy {
    String notificar(Pedido pedido);
}
