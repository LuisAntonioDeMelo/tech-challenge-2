package com.tech.challenge.fastfood.adapter.driven.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_entity_id")
    private ClienteEntity clienteEntity;

    @ManyToMany
    @JoinTable(
            name = "pedido_entity_produto_entity",
            joinColumns = @JoinColumn(name = "pedido_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_entity_id")
    )
    private List<ProdutoEntity> produtos;

    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFinalizacao;

    @Enumerated(EnumType.ORDINAL)
    private SituacaoPedidoEnum situacaoPedidoEnum;

    private BigDecimal valorTotalPedido;

}
