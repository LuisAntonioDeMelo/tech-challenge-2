package com.tech.challenge.fastfood.core.application.services;

import com.tech.challenge.fastfood.core.application.ports.ProdutoRepositoryPort;
import com.tech.challenge.fastfood.core.application.ports.ProdutoServicePort;
import com.tech.challenge.fastfood.core.application.services.exceptions.ProdutoInexistenteException;
import com.tech.challenge.fastfood.core.domain.Produto;

import java.util.List;
import java.util.Optional;

public class ProdutoService implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public ProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public Produto criarProduto(Produto produto) {
        return produtoRepositoryPort.criarProduto(produto);
    }

    @Override
    public Produto alterarProduto(Produto produto) {
        Optional<Produto> produtoOptional =  produtoRepositoryPort.obterPorId(produto.getId());
        if(produtoOptional.isEmpty()) {
            throw new ProdutoInexistenteException("Produto não encontrato :" + produto.getNomeProduto());
        }
        return produtoRepositoryPort.alterarProduto(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        Optional<Produto> produtoOptional =  produtoRepositoryPort.obterPorId(id);
        if(produtoOptional.isEmpty()) {
            throw new ProdutoInexistenteException("Produto não encontrato ");
        }
        produtoRepositoryPort.deletarProduto(id);
    }

    @Override
    public List<Produto> obterProdutos() {
        return produtoRepositoryPort.listarProdutos();
    }

    @Override
    public List<Produto> obterProdutosPorCategoria(String categoria) {
        return produtoRepositoryPort.listarProdutosPorCategoria(categoria);
    }

}
