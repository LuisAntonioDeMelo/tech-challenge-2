package com.tech.challenge.fastfood.infrastructure.controllers;

import com.tech.challenge.fastfood.application.usecases.pedidos.ListarPedidosPorSituacaoUseCase;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoStatusResponseDTO;
import com.tech.challenge.fastfood.infrastructure.persistence.converters.PedidoConverter;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoRequest;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoResponseDTO;
import com.tech.challenge.fastfood.application.usecases.pedidos.PedidoUseCase;
import com.tech.challenge.fastfood.domain.Pedido;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoUseCase pedidoUseCase;
    private final PedidoConverter pedidoConverter;
    private final ListarPedidosPorSituacaoUseCase listarPedidosPorSituacaoUseCase;

    @GetMapping()
    public ResponseEntity<List<PedidoResponseDTO>> obterPedidos() {
        List<Pedido> pedidos = pedidoUseCase.listarPedidos();
        return ResponseEntity.ok(pedidos.stream().map(pedidoConverter::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoUseCase.criarPedido(pedidoConverter.toDomain(pedidoRequest));
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }

    @PutMapping("alterar-situacao/{id}")
    public ResponseEntity<?> alterarSituacaoPedido(@PathVariable Long id, @RequestParam String situacao) {
        Pedido pedido = pedidoUseCase.alterarSituacaoPedido(id, situacao);
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }

    @GetMapping("/cliente")
    public ResponseEntity<PedidoResponseDTO> obterPedidoPorCPF(@RequestParam String cpf) {
        Pedido pedido = pedidoUseCase.obterPedidoPorCPF(cpf);
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }

    @GetMapping("/checkout/{id}")
    public ResponseEntity<?> checkoutPedido(@PathVariable Long id) {
        Pedido pedido = pedidoUseCase.checkoutPedido(id);
        return ResponseEntity.ok(pedidoConverter.toDto(pedido));
    }

    @GetMapping("/situacao-ordenados")
    public ResponseEntity<List<PedidoStatusResponseDTO>> obterPedidosPorSituacao(@RequestParam(required = false) String situacao) {
        List<Pedido> pedidos = listarPedidosPorSituacaoUseCase.listarPedidosPorSituacao(situacao);
        return ResponseEntity.ok(pedidos.stream().map(pedidoConverter::toStatusDto).toList());
    }

}
