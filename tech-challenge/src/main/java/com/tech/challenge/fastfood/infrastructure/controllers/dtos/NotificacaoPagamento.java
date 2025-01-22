package com.tech.challenge.fastfood.infrastructure.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoPagamento {

    private String paymentId;
    private String externalReference;
    private Map<String, Object> payload;
}
