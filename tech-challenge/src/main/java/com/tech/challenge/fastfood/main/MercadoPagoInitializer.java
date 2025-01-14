package com.tech.challenge.fastfood.main;

import com.mercadopago.MercadoPagoConfig;

public class MercadoPagoInitializer {

    public static void initialize() {
        MercadoPagoConfig.setAccessToken("SEU_ACCESS_TOKEN");
    }
}
