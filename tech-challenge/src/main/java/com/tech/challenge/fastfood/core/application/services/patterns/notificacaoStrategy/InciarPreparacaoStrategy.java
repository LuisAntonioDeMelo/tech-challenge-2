package com.tech.challenge.fastfood.core.application.services.patterns.notificacaoStrategy;

import com.tech.challenge.fastfood.core.application.services.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.core.domain.Pedido;

public class InciarPreparacaoStrategy implements NotificacaoStrategy {
    @Override
    public String notificar(Pedido pedido) {
        return "Pedido Começou a ser preparado";
    }
}
