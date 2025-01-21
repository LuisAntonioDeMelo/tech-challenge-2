package com.tech.challenge.fastfood.application.usecases.pagamento.interactors;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.resources.payment.Payment;
import com.tech.challenge.fastfood.application.usecases.pagamento.NotificacaoPagamentoUseCase;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.NotificacaoPagamento;

public class NotificacaoPagamentoInteractor implements NotificacaoPagamentoUseCase {

    @Override
    public void processarPagamentoAprovado(NotificacaoPagamento notificacao) {
//        MercadoPagoConfig.setAccessToken("SEU_ACCESS_TOKEN");
//        PaymentClient paymentClient = new PaymentClient();
//        Payment payment = paymentClient.get(paymentId);
//        String status = payment.getStatus();
//        if (status.equals("approved")) {
//            // Atualizar status do pedido
//        }
    }

    @Override
    public void processarPagamentoRecusado(NotificacaoPagamento notificacao) {

    }
}
