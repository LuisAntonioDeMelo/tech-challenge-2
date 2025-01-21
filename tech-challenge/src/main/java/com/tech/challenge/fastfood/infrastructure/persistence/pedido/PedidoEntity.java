package com.tech.challenge.fastfood.infrastructure.persistence.pedido;

import com.tech.challenge.fastfood.infrastructure.persistence.cliente.ClienteEntity;
import com.tech.challenge.fastfood.infrastructure.persistence.pagamento.PagamentoEntity;
import com.tech.challenge.fastfood.infrastructure.persistence.produto.ProdutoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToOne(mappedBy = "pedidoEntity", cascade = CascadeType.ALL)
    private PagamentoEntity pagamentoEntity;

    @ManyToMany
    @JoinTable(
            name = "pedido_entity_produto_entity",
            joinColumns = @JoinColumn(name = "pedido_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_entity_id")
    )
    private List<ProdutoEntity> produtos;

    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFinalizacao;

    @Enumerated(EnumType.STRING)
    private SituacaoPedidoEnum situacaoPedidoEnum;

    private BigDecimal valorTotalPedido;

}
