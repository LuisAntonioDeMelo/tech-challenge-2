package com.tech.challenge.fastfood.application.gateway;

import com.tech.challenge.fastfood.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {
    public Produto criarProduto(Produto produto);
    public Produto alterarProduto(Produto produto);
    public void deletarProduto(Long id);
    public List<Produto> listarProdutos();
    public List<Produto> listarProdutosPorCategoria(String categoria);
    public Optional<Produto> obterPorId(Long id);
}
