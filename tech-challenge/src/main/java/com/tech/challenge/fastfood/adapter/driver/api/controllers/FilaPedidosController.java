package com.tech.challenge.fastfood.adapter.driver.api.controllers;

import com.tech.challenge.fastfood.adapter.driver.api.dtos.PedidoFilaDTO;
import com.tech.challenge.fastfood.core.application.ports.FilaPedidosServicePort;
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

    private final FilaPedidosServicePort filaPedidosServicePort;

    @GetMapping("/fila")
    public ResponseEntity<List<PedidoFilaDTO>> obterFilaDePedidos() {
        return ResponseEntity.ok(  filaPedidosServicePort.listarPedidosNaFila());
    }

    @GetMapping("/pedidos-retirada")
    public ResponseEntity<List<PedidoFilaDTO>> obterFilaDePedidosRetirada() {
        return ResponseEntity.ok(  filaPedidosServicePort.listarPedidosNaFilaRetirada());
    }

}
