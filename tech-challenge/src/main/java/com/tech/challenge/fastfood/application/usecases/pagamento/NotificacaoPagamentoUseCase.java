package com.tech.challenge.fastfood.application.usecases.pagamento;

import com.tech.challenge.fastfood.infrastructure.controllers.dtos.NotificacaoPagamento;

public interface NotificacaoPagamentoUseCase {
    void processarPagamentoAprovado(NotificacaoPagamento notificacao);
    void processarPagamentoRecusado(NotificacaoPagamento notificacao);
}
