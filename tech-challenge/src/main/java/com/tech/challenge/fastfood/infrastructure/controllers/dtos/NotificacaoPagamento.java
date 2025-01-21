package com.tech.challenge.fastfood.infrastructure.controllers.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NotificacaoPagamento {
    private String notification_type;
    private Message message;

    @Data
    public static class Message {
        private String reference_code;
        private String external_reference;
        private double value;
        private String status;
        private String payment_date;
        private String end_to_end;
    }

}
