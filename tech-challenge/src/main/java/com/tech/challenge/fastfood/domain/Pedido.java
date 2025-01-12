package com.tech.challenge.fastfood.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record Pedido(
        Long idPedido,
        String codigoPedido,
        Cliente cliente,
        List<Produto> produtos,
        LocalDateTime horarioInicio,
        LocalDateTime horarioFinalizacao,
        BigDecimal valorTotalPedido,
        SituacaoPedido situacaoPedido
) {
}