package com.tech.challenge.fastfood.application.usecases.pagamento.pagamentoStrategy.qrCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MercadoPagoPixResponse {

    @JsonProperty("point_of_interaction")
    private PointOfInteraction pointOfInteraction;
}
