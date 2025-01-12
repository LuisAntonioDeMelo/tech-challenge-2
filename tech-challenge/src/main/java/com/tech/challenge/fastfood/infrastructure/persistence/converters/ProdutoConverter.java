package com.tech.challenge.fastfood.infrastructure.persistence.converters;

import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ProdutoRequest;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ProdutoResponseDTO;
import com.tech.challenge.fastfood.domain.CategoriaProduto;
import com.tech.challenge.fastfood.domain.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoConverter {

    public Produto toDomain(ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        produto.setNomeProduto(produtoRequest.nomeProduto());
        produto.setAltura(produtoRequest.altura());
        produto.setLargura(produtoRequest.largura());
        produto.setDescricao(produtoRequest.descricao());
        produto.setPeso(produtoRequest.peso());
        produto.setEAN(produtoRequest.EAN());
        produto.setValor(produtoRequest.valor());
        produto.setCategoriaProduto(CategoriaProduto.obterPorNome(produtoRequest.categoria()));
        return produto;
    }

    public Produto toDomain(Long id, ProdutoRequest produtoRequest) {
        Produto produto =  toDomain(produtoRequest);
        produto.setId(id);
        return produto;
    }

    public ProdutoResponseDTO toDto(Produto produto) {
        return ProdutoResponseDTO
                .builder()
                .id(produto.getId())
                .categoria(produto.getCategoriaProduto().name())
                .nomeProduto(produto.getNomeProduto())
                .descricao(produto.getDescricao())
                .valor(produto.getValor())
                .peso(produto.getPeso())
                .altura(produto.getAltura())
                .largura(produto.getLargura())
                .EAN(produto.getEAN())
                .build();
    }
}
