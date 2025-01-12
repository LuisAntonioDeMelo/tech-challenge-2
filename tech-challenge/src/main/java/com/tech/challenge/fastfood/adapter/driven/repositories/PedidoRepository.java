package com.tech.challenge.fastfood.adapter.driven.repositories;

import com.tech.challenge.fastfood.adapter.driven.persistence.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    @Query("""
            SELECT p From PedidoEntity p 
            JOIN FETCH p.clienteEntity c 
            WHERE c.cpf = :cpf
            """)
    PedidoEntity oterPedidoPorCPF(@Param("cpf") String cpf);
}
