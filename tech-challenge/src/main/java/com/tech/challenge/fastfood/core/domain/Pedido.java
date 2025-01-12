package com.tech.challenge.fastfood.core.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Pedido {

    private Long idPedido;
    private String codigoPedido;
    private Cliente cliente;
    private List<Produto> produtos;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFinalizacao;
    private BigDecimal valorTotalPedido;
    private SituacaoPedido situacaoPedido;

}
