package com.tech.challenge.fastfood.application.usecases.pagamento.interactors;

import com.tech.challenge.fastfood.application.gateway.PagamentoGateway;
import com.tech.challenge.fastfood.application.usecases.pagamento.NotificacaoPagamentoUseCase;
import com.tech.challenge.fastfood.domain.Pagamento;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.NotificacaoPagamento;

public class NotificacaoPagamentoInteractor implements NotificacaoPagamentoUseCase {

    private final PagamentoGateway pagamentoGateway;

    public NotificacaoPagamentoInteractor(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public Pagamento processarPagamentoAprovado(NotificacaoPagamento notificacao) {
        Pagamento pagamento = pagamentoGateway.consultarStatusPagamento(Long.getLong(notificacao.getPaymentId()));
        pagamento = pagamentoGateway.aprovarPagamento(pagamento.getId(), "APROVADO");
        return  pagamento;
    }

}
