package com.tech.challenge.fastfood.application.usecases.pagamento.interactors;

import com.tech.challenge.fastfood.application.gateway.PagamentoGateway;
import com.tech.challenge.fastfood.application.usecases.pagamento.ConsultarPagamentoUseCase;
import com.tech.challenge.fastfood.domain.Pagamento;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.StatusPagamentoDTO;

public class ConsultarPagamentoInteractor implements ConsultarPagamentoUseCase {
    private final PagamentoGateway pagamentoGateway;

    public ConsultarPagamentoInteractor(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public StatusPagamentoDTO consultarStatusPagamento(Long idPedido) {
        Pagamento pagamento = pagamentoGateway.consultarStatusPagamento(idPedido);
        if (pagamento == null) {
            throw new IllegalArgumentException("Pedido não possui pagamento");
        }
        return  StatusPagamentoDTO.dto(pagamento);
    }
}
