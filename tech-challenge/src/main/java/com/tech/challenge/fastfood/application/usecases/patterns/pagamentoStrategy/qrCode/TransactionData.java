package com.tech.challenge.fastfood.application.usecases.patterns.pagamentoStrategy.qrCode;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionData {
    @JsonProperty("qr_code")
    private String emv;

    @JsonProperty("qr_code_base64")
    private String base64;
}
