package com.tech.challenge.fastfood.infrastructure.controllers.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProdutoResponseDTO {

    private Long id;
    private String nomeProduto;
    private String descricao;
    private Double largura;
    private Double altura;
    private String EAN;
    private BigDecimal peso;
    private BigDecimal valor;
    private String categoria;
    private Long idPedido;

}
