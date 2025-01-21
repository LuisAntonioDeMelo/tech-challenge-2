package com.tech.challenge.fastfood.infrastructure.controllers;

import com.tech.challenge.fastfood.infrastructure.persistence.converters.ProdutoConverter;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ProdutoRequest;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ProdutoResponseDTO;
import com.tech.challenge.fastfood.application.usecases.produto.ProdutoUseCase;
import com.tech.challenge.fastfood.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoUseCase produtoUseCase;
    private final ProdutoConverter produtoConverter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProdutoResponseDTO>> listarProduto() {
        List<Produto> produtos = produtoUseCase.obterProdutos();
        return ResponseEntity.ok(produtos.stream().map(produtoConverter::toDto).toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoRequest produtoRequest) {
        Produto produto = produtoUseCase.criarProduto(produtoConverter.toDomain(produtoRequest));
        return ResponseEntity.ok(produtoConverter.toDto(produto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdutoResponseDTO> alterarProduto(@PathVariable Long id, @RequestBody ProdutoRequest produtoRequest) {
        Produto produto = produtoUseCase.alterarProduto(produtoConverter.toDomain(id, produtoRequest));
        return ResponseEntity.ok(produtoConverter.toDto(produto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProdutoResponseDTO> deletarProduto(@PathVariable Long id) {
        produtoUseCase.deletarProduto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProdutoResponseDTO>> obterProdutosPorCategoria(@RequestParam("categoria") String categoria) {
        List<Produto> produtos =  produtoUseCase.obterProdutosPorCategoria(categoria);
        return ResponseEntity.ok(produtos.stream().map(produtoConverter::toDto).toList());
    }

}
