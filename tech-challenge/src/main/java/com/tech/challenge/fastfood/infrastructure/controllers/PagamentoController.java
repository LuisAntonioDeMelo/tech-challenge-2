package com.tech.challenge.fastfood.infrastructure.controllers;

import com.tech.challenge.fastfood.application.usecases.ProcessarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.interactors.ProcessarPagamentoInteractor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;

    @PostMapping("qr-code")
    public String pagamentoQrCode(@RequestParam("idPedido") Long idPedido) {
        return processarPagamentoUseCase.processarPagamento(idPedido);
    }
}
