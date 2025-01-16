package com.tech.challenge.fastfood.application.usecases.pagamento;

import com.tech.challenge.fastfood.infrastructure.controllers.dtos.StatusPagamentoDTO;

public interface ConsultarPagamentoUseCase {
    StatusPagamentoDTO consultarStatusPagamento(Long idPedido);
}
