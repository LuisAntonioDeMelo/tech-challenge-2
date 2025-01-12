package com.tech.challenge.fastfood.application.usecases.patterns;

import com.tech.challenge.fastfood.domain.Pedido;

public interface NotificacaoStrategy {
    String notificar(Pedido pedido);
}
