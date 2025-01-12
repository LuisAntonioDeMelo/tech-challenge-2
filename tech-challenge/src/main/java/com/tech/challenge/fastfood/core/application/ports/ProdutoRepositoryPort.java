package com.tech.challenge.fastfood.core.application.ports;

import com.tech.challenge.fastfood.core.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryPort {
    public Produto criarProduto(Produto produto);
    public Produto alterarProduto(Produto produto);
    public void deletarProduto(Long id);
    public List<Produto> listarProdutos();
    public List<Produto> listarProdutosPorCategoria(String categoria);
    public Optional<Produto> obterPorId(Long id);
}
