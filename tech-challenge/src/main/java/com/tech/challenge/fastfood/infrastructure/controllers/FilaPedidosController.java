package com.tech.challenge.fastfood.infrastructure.controllers;

import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoFilaDTO;
import com.tech.challenge.fastfood.application.usecases.pedidos.FilaPedidosUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/filaPedidos")
@RequiredArgsConstructor
public class FilaPedidosController {

    private final FilaPedidosUseCase filaPedidosUseCase;

    @GetMapping("/fila")
    public ResponseEntity<List<PedidoFilaDTO>> obterFilaDePedidos() {
        return ResponseEntity.ok(  filaPedidosUseCase.listarPedidosNaFila());
    }

    @GetMapping("/pedidos-prontos")
    public ResponseEntity<List<PedidoFilaDTO>> obterFilaDePedidosRetirada() {
        return ResponseEntity.ok(filaPedidosUseCase.listarPedidosProntos());
    }

}
