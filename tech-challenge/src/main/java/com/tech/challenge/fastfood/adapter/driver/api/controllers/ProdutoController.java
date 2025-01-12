package com.tech.challenge.fastfood.adapter.driver.api.controllers;

import com.tech.challenge.fastfood.adapter.driven.converters.ProdutoConverter;
import com.tech.challenge.fastfood.adapter.driver.api.dtos.ProdutoRequest;
import com.tech.challenge.fastfood.adapter.driver.api.dtos.ProdutoResponseDTO;
import com.tech.challenge.fastfood.core.application.ports.ProdutoServicePort;
import com.tech.challenge.fastfood.core.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoServicePort produtoServicePort;
    private final ProdutoConverter produtoConverter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProdutoResponseDTO>> listarProduto() {
        List<Produto> produtos = produtoServicePort.obterProdutos();
        return ResponseEntity.ok(produtos.stream().map(produtoConverter::toDto).toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoRequest produtoRequest) {
        Produto produto = produtoServicePort.criarProduto(produtoConverter.toDomain(produtoRequest));
        return ResponseEntity.ok(produtoConverter.toDto(produto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdutoResponseDTO> alterarProduto(@PathVariable Long id, @RequestBody ProdutoRequest produtoRequest) {
        Produto produto = produtoServicePort.alterarProduto(produtoConverter.toDomain(id, produtoRequest));
        return ResponseEntity.ok(produtoConverter.toDto(produto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProdutoResponseDTO> deletarProduto(@PathVariable Long id) {
        produtoServicePort.deletarProduto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProdutoResponseDTO>> obterProdutosPorCategoria(@RequestParam("categoria") String categoria) {
        List<Produto> produtos =  produtoServicePort.obterProdutosPorCategoria(categoria);
        return ResponseEntity.ok(produtos.stream().map(produtoConverter::toDto).toList());
    }

}
