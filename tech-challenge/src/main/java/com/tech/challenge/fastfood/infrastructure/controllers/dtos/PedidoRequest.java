package com.tech.challenge.fastfood.infrastructure.controllers.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoRequest(
        Long idCliente,
        List<ProdutoDTO> produtos,
        String situcaoPedido,
        LocalDateTime horario
) {
}
