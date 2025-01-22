package com.tech.challenge.fastfood.application.usecases.pagamento;

import com.tech.challenge.fastfood.domain.Pagamento;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.NotificacaoPagamento;

public interface NotificacaoPagamentoUseCase {
    Pagamento processarPagamentoAprovado(NotificacaoPagamento notificacao);
}
