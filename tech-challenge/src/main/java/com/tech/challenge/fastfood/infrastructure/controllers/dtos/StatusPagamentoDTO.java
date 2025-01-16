package com.tech.challenge.fastfood.infrastructure.controllers.dtos;

import com.tech.challenge.fastfood.domain.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusPagamentoDTO {

    private Long id;
    private BigDecimal valor;
    private String descricao;
    private TipoPagamento tipoPagamento;

}
