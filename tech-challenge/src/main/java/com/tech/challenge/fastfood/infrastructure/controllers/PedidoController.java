package com.tech.challenge.fastfood.infrastructure.controllers;

import com.tech.challenge.fastfood.infrastructure.persistence.converters.PedidoConverter;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoRequest;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoResponseDTO;
import com.tech.challenge.fastfood.application.exception.application.ports.PedidoServicePort;
import com.tech.challenge.fastfood.domain.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoServicePort pedidoServicePort;
    private final PedidoConverter pedidoConverter;

    @GetMapping()
    public ResponseEntity<List<PedidoResponseDTO>> obterPedidos() {
        List<Pedido> pedidos = pedidoServicePort.listarPedidos();
        return ResponseEntity.ok(pedidos.stream().map(pedidoConverter::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoServicePort.criarPedido(pedidoConverter.toDomain(pedidoRequest));
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterarSituacaoPedido(@PathVariable Long id, @RequestParam String situacao) {
        Pedido pedido = pedidoServicePort.alterarSituacaoPedido(id, situacao);
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }

    @GetMapping("/cliente")
    public ResponseEntity<PedidoResponseDTO> obterPedidoPorCPF(@RequestParam String cpf) {
        Pedido pedido = pedidoServicePort.obterPedidoPorCPF(cpf);
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }

    @GetMapping("/checkout/{id}")
    public ResponseEntity<?> checkouttPedido(@PathVariable Long id) {
        Pedido pedido = pedidoServicePort.checkoutPedido(id);
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }


}
