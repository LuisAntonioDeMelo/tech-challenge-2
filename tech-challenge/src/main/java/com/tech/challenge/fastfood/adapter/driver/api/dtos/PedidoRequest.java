package com.tech.challenge.fastfood.adapter.driver.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoRequest(
        Long idCliente,
        List<ProdutoDTO> produtos,
        String situcaoPedido,
        LocalDateTime horario
) {
}
