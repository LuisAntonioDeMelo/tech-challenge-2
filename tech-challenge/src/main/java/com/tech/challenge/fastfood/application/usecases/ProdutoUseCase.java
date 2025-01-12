package com.tech.challenge.fastfood.application.usecases;

import com.tech.challenge.fastfood.domain.Produto;

import java.util.List;

public interface ProdutoUseCase {
    public Produto criarProduto(Produto produto);
    public Produto alterarProduto(Produto produto);
    public void deletarProduto(Long id);
    public List<Produto> obterProdutos();
    public List<Produto> obterProdutosPorCategoria(String categoria);
}
