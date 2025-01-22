package com.tech.challenge.fastfood.infrastructure.controllers;

import com.tech.challenge.fastfood.application.usecases.pagamento.ConsultarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.pagamento.CriarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.pagamento.NotificacaoPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.pagamento.ProcessarPagamentoUseCase;
import com.tech.challenge.fastfood.domain.Pedido;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.NotificacaoPagamento;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.StatusPagamentoDTO;
import com.tech.challenge.fastfood.infrastructure.persistence.converters.PedidoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("v1/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;
    private final ConsultarPagamentoUseCase consultarPagamentoUseCase;
    private final CriarPagamentoUseCase criarPagamentoPedidoUseCase;
    private final NotificacaoPagamentoUseCase notificacaoPagamentoUseCase;
    private final PedidoConverter pedidoConverter;


    @PostMapping("/webhook/notificacao")
    public ResponseEntity<String> receberNotificacaoPagamento(@RequestBody Map<String,Object> map) {
        System.out.println("Notificação recebida: " + map);
        if ("paid".equals(map.get(""))) {

        } else {
            return ResponseEntity.badRequest().body("Status de pagamento desconhecido.");
        }
        return ResponseEntity.ok("Notificação processada com sucesso.");
    }

    @PostMapping
    public ResponseEntity<String> handleNotification(@RequestBody Map<String, Object> payload) {
        System.out.println("Notificação recebida: " + payload);
        String paymentId = (String) payload.get("id");
        String externalReference = (String) payload.get("external_reference");

        System.out.println("Payment ID: " + paymentId);
        System.out.println("External Reference: " + externalReference);
        notificacaoPagamentoUseCase.processarPagamentoAprovado(new NotificacaoPagamento(paymentId, externalReference, payload));

        return ResponseEntity.ok("OK");
    }


    @GetMapping("/{idPedido}/status")
    public ResponseEntity<StatusPagamentoDTO> consultarStatusPagamento(@PathVariable Long idPedido) {
        return ResponseEntity.ok(consultarPagamentoUseCase.consultarStatusPagamento(idPedido));
    }


    @GetMapping("qr-code")
    public ResponseEntity<?> pagamentoQrCode(@RequestParam("idPedido") Long idPedido) {
        return ResponseEntity.ok(processarPagamentoUseCase.processarPagamento(idPedido));
    }

    @PostMapping("/criar-pagamento-pedido")
    public ResponseEntity<?> criarPagamentoPedido(
            @RequestParam("idPedido") Long id,
            @RequestParam("tipoPagamento") String tipoPagamento
    ) {
        Pedido pedido = criarPagamentoPedidoUseCase.criarPagamentoPedido(id, tipoPagamento);
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }


}
