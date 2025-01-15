package com.tech.challenge.fastfood.infrastructure.persistence;

import com.tech.challenge.fastfood.domain.TipoPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedido_entity_id")
    private PedidoEntity pedidoEntity;

    private BigDecimal valor;
    private String descricao;
    private String emv;
    private String base64;
    private LocalDateTime expiracao;
    private TipoPagamento tipoPagamento;
}
