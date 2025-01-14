package com.tech.challenge.fastfood.application.usecases.patterns.pagamentoStrategy.qrCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PointOfInteraction {
    @JsonProperty("transaction_data")
    private TransactionData transactionData;
}
