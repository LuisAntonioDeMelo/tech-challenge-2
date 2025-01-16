package com.tech.challenge.fastfood.application.usecases.pagamento;

import com.tech.challenge.fastfood.domain.Pedido;

public interface CriarPagamentoUseCase {

    Pedido criarPagamentoPedido(Long id, String tipoPagamento);
}
