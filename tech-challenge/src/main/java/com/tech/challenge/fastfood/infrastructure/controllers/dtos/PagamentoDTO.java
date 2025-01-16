package com.tech.challenge.fastfood.infrastructure.controllers.dtos;

import com.tech.challenge.fastfood.domain.TipoPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {

    private Long id;
    private BigDecimal valor;
    private String descricao;
    private String emv;
    private String base64;
    private LocalDateTime expiracao;
    private TipoPagamento tipoPagamento;
}
