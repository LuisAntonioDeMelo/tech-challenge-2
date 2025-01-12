package com.tech.challenge.fastfood.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Long id;
    private String nomeProduto;
    private String descricao;
    private Double largura;
    private Double altura;
    private String EAN;
    private BigDecimal peso;
    private BigDecimal valor;
    private CategoriaProduto categoriaProduto;
    private List<Pedido> pedidos;

    public Produto(Long id, String ean) {
        this.id = id;
        this.EAN = ean;
    }
}
