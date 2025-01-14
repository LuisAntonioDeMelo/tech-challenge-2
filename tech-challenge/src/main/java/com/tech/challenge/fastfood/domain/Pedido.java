package com.tech.challenge.fastfood.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Long idPedido;
    private String codigoPedido;
    private Cliente cliente;
    private List<Produto> produtos;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFinalizacao;
    private BigDecimal valorTotalPedido;
    private SituacaoPedido situacaoPedido;
    private Pagamento pagamento;
}