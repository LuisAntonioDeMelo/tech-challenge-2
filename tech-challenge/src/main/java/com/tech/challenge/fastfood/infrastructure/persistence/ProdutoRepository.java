package com.tech.challenge.fastfood.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long> {
    ProdutoEntity findByEAN(String ean);
    List<ProdutoEntity> findAllByCategoriaProdutoEnum(CategoriaProdutoEnum categoriaProdutoEnum);
}
