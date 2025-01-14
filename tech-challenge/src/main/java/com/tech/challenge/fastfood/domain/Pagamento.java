package com.tech.challenge.fastfood.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Pagamento {

    private UUID id;
    private BigDecimal valor;
    private String descricao;
    private String emv;
    private String base64;
    private LocalDateTime expiracao;
    private TipoPagamento tipoPagamento;
}
