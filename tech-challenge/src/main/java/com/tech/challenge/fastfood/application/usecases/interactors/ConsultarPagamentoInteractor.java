package com.tech.challenge.fastfood.application.usecases.interactors;

import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.pagamento.ConsultarPagamentoUseCase;
import com.tech.challenge.fastfood.domain.Pagamento;
import com.tech.challenge.fastfood.domain.Pedido;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.StatusPagamentoDTO;

public class ConsultarPagamentoInteractor implements ConsultarPagamentoUseCase {
    private final PedidoGateway pedidoGateway;

    public ConsultarPagamentoInteractor(PedidoGateway pedidoGateway) {
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public StatusPagamentoDTO consultarStatusPagamento(Long idPedido) {
        Pedido pedido = pedidoGateway.obterPedidoPorId(idPedido);
        Pagamento pagamento = pedido.getPagamento();
        if (pagamento == null) {
            throw new IllegalArgumentException("Pedido não possui pagamento");
        }
        StatusPagamentoDTO statusPagamentoDTO = new StatusPagamentoDTO();
        statusPagamentoDTO.setId(pagamento.getId());
        statusPagamentoDTO.setValor(pagamento.getValor());
        statusPagamentoDTO.setDescricao(pagamento.getDescricao());
        statusPagamentoDTO.setTipoPagamento(pagamento.getTipoPagamento());
        return statusPagamentoDTO;
    }
}
