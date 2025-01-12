package com.tech.challenge.fastfood.adapter.driver.api.dtos;

import com.tech.challenge.fastfood.core.domain.Cliente;
import com.tech.challenge.fastfood.core.domain.Produto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class PedidoResponseDTO {

    private Long idPedido;
    private String codigoPedido;
    private Cliente cliente;
    private List<ProdutoDTO> produtos;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFinalizacao;
    private String situacaoPedido;
    private BigDecimal valorTotal;
}
