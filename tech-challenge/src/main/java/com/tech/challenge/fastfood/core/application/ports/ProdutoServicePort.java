package com.tech.challenge.fastfood.core.application.ports;

import com.tech.challenge.fastfood.core.domain.Produto;

import java.util.List;

public interface ProdutoServicePort {
    public Produto criarProduto(Produto produto);
    public Produto alterarProduto(Produto produto);
    public void deletarProduto(Long id);
    public List<Produto> obterProdutos();
    public List<Produto> obterProdutosPorCategoria(String categoria);
}
