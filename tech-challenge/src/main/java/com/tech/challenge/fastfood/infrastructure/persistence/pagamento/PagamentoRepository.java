package com.tech.challenge.fastfood.infrastructure.persistence.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long> {

    @Query("""
            SELECT p From PagamentoEntity p 
            JOIN FETCH p.pedidoEntity pe 
            WHERE pe.id = :idPedido
            """)
    PagamentoEntity obterPagamentoPorIdPedido(Long idPedido);
}
