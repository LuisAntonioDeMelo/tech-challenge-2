package com.tech.challenge.fastfood.infrastructure.controllers;

import com.tech.challenge.fastfood.application.usecases.pagamento.ConsultarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.pagamento.CriarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.pagamento.ProcessarPagamentoUseCase;
import com.tech.challenge.fastfood.domain.Pedido;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.StatusPagamentoDTO;
import com.tech.challenge.fastfood.infrastructure.persistence.converters.PedidoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;
    private final ConsultarPagamentoUseCase consultarPagamentoUseCase;
    private final CriarPagamentoUseCase criarPagamentoPedidoUseCase;
    private final PedidoConverter pedidoConverter;

    @GetMapping("/{idPedido}/status")
    public ResponseEntity<StatusPagamentoDTO> consultarStatusPagamento(@PathVariable Long idPedido) {
        return ResponseEntity.ok(consultarPagamentoUseCase.consultarStatusPagamento(idPedido));
    }
    @PostMapping("qr-code")
    public ResponseEntity<?> pagamentoQrCode(@RequestParam("idPedido") Long idPedido) {
        return ResponseEntity.ok(processarPagamentoUseCase.processarPagamento(idPedido));
    }

    @PostMapping("/criar-pagamento-pedido/{id}")
    public ResponseEntity<?> criarPagamentoPedido(@PathVariable Long id, @RequestParam String tipoPagamento) {
        Pedido pedido = criarPagamentoPedidoUseCase.criarPagamentoPedido(id, tipoPagamento);
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }


}
