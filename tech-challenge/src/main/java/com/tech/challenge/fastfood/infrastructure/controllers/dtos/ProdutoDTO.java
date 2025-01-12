package com.tech.challenge.fastfood.infrastructure.controllers.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProdutoDTO {
    private Long id;
    private String EAN;
    private String nome;
    private BigDecimal valor;
    private String descricao;
}
