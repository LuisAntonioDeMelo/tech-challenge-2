package com.tech.challenge.fastfood.adapter.driven.repositories;

import com.tech.challenge.fastfood.adapter.driven.persistence.CategoriaProdutoEnum;
import com.tech.challenge.fastfood.adapter.driven.persistence.ProdutoEntity;
import com.tech.challenge.fastfood.core.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long> {
    ProdutoEntity findByEAN(String ean);
    List<ProdutoEntity> findAllByCategoriaProdutoEnum(CategoriaProdutoEnum categoriaProdutoEnum);
}
