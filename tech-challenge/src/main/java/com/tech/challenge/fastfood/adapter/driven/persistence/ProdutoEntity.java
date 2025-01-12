package com.tech.challenge.fastfood.adapter.driven.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;
    private String descricao;
    private Double largura;
    private Double altura;
    private String EAN;
    private BigDecimal peso;
    private BigDecimal valor;

    @Enumerated(EnumType.ORDINAL)
    private CategoriaProdutoEnum categoriaProdutoEnum;

    @ManyToMany(mappedBy = "produtos")
    private List<PedidoEntity> pedidos;

}
