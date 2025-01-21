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
    public ResponseEntity<String> receberNotificacaoPagamento(@RequestBody NotificacaoPagamento notificacao) {
        System.out.println("Notificação recebida: " + notificacao);
        if ("paid".equals(notificacao.getMessage().getStatus())) {
            notificacaoPagamentoUseCase.processarPagamentoAprovado(notificacao);
        } else {
            return ResponseEntity.ba33333333333dRequest().body("Status de pagamento desconhecido.");
        }

        return ResponseEntity.ok("Notificação processada com sucesso.");
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
