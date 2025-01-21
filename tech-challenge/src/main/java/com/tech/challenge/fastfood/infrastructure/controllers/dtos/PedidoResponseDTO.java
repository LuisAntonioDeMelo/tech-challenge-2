package com.tech.challenge.fastfood.infrastructure.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tech.challenge.fastfood.domain.Cliente;
import com.tech.challenge.fastfood.domain.Pagamento;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime horarioInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime horarioFinalizacao;

    private String situacaoPedido;

    private BigDecimal valorTotal;

    private Pagamento pagamento;
}
