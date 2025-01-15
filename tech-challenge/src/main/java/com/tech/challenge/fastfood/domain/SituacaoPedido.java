package com.tech.challenge.fastfood.domain;

import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.patterns.NotificacaoStrategy;
import com.tech.challenge.fastfood.application.usecases.patterns.notificacaoStrategy.*;

import java.util.Arrays;

public enum SituacaoPedido {
    CRIADO(new CriarPedidoStrategy()),
    INCIAR_PREPARACAO(new InciarPreparacaoStrategy()),
    EM_PREPARACAO(new PedidoEmPreparacaoStrategy()),
    MONTAGEM(new PedidoEmMontagemStrategy()),
    EM_FILA_RETIRADA(new PedidoEmFilaRetiradaStrategy()),
    FINALIZADO(new FinalizarPedidoStrategy());

    private final NotificacaoStrategy notificacaoStrategy;

    SituacaoPedido(NotificacaoStrategy notificacaoStrategy) {
        this.notificacaoStrategy = notificacaoStrategy;
    }

    public NotificacaoStrategy getStrategy() {
        return notificacaoStrategy;
    }

    public static SituacaoPedido obter(String situacao) {
        return Arrays.stream(SituacaoPedido.values())
                .filter(ct -> ct.name().equalsIgnoreCase(situacao))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Situação não existe : " + situacao));
    }
}
