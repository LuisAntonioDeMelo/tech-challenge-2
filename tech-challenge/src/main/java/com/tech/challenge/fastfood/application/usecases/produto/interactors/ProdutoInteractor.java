package com.tech.challenge.fastfood.application.usecases.produto.interactors;

import com.tech.challenge.fastfood.application.gateway.ProdutoGateway;
import com.tech.challenge.fastfood.application.usecases.produto.ProdutoUseCase;
import com.tech.challenge.fastfood.application.usecases.produto.exceptions.ProdutoInexistenteException;
import com.tech.challenge.fastfood.domain.Produto;

import java.util.List;
import java.util.Optional;

public class ProdutoInteractor implements ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoInteractor(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto criarProduto(Produto produto) {
        return produtoGateway.criarProduto(produto);
    }

    @Override
    public Produto alterarProduto(Produto produto) {
        Optional<Produto> produtoOptional =  produtoGateway.obterPorId(produto.getId());
        if(produtoOptional.isEmpty()) {
            throw new ProdutoInexistenteException("Produto não encontrato :" + produto.getNomeProduto());
        }
        return produtoGateway.alterarProduto(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        Optional<Produto> produtoOptional =  produtoGateway.obterPorId(id);
        if(produtoOptional.isEmpty()) {
            throw new ProdutoInexistenteException("Produto não encontrato ");
        }
        produtoGateway.deletarProduto(id);
    }

    @Override
    public List<Produto> obterProdutos() {
        return produtoGateway.listarProdutos();
    }

    @Override
    public List<Produto> obterProdutosPorCategoria(String categoria) {
        return produtoGateway.listarProdutosPorCategoria(categoria);
    }

}
