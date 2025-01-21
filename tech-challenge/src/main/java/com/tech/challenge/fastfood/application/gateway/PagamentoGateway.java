package com.tech.challenge.fastfood.application.gateway;

import com.tech.challenge.fastfood.domain.Pagamento;

public interface PagamentoGateway {
    Pagamento consultarStatusPagamento(Long idPedido);
}
