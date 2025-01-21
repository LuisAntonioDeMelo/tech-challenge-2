package com.tech.challenge.fastfood.infrastructure.persistence.converters;

import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoRequest;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoResponseDTO;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.PedidoStatusResponseDTO;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ProdutoDTO;
import com.tech.challenge.fastfood.domain.Cliente;
import com.tech.challenge.fastfood.domain.Pedido;
import com.tech.challenge.fastfood.domain.Produto;
import com.tech.challenge.fastfood.domain.SituacaoPedido;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PedidoConverter {

    public Pedido toDomain(PedidoRequest pedidoRequest) {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente();
        cliente.setId(pedidoRequest.idCliente());
        pedido.setCliente(cliente);
        pedido.setSituacaoPedido(SituacaoPedido.obter(pedidoRequest.situcaoPedido()));
        pedido.setHorarioInicio(pedidoRequest.horario());
        pedido.setProdutos(pedidoRequest.produtos()
                .stream()
                .map(p -> new Produto(p.getId(), p.getQuantidade(), p.getEAN()))
                .toList());
        return pedido;
    }

    public PedidoResponseDTO toDto(Pedido pedido) {
        return PedidoResponseDTO.builder()
                .idPedido(pedido.getIdPedido())
                .codigoPedido(pedido.getCodigoPedido())
                .situacaoPedido(!Objects.isNull(pedido.getSituacaoPedido()) ? pedido.getSituacaoPedido().name() : null)
                .cliente(pedido.getCliente())
                .horarioInicio(pedido.getHorarioInicio())
                .horarioFinalizacao(pedido.getHorarioFinalizacao())
                .produtos(pedido.getProdutos()
                        .stream()
                        .map(p ->
                                ProdutoDTO.builder()
                                        .id(p.getId())
                                        .nome(p.getNomeProduto())
                                        .valor(p.getValor())
                                        .EAN(p.getEAN())
                                        .descricao(p.getDescricao())
                                        .build())
                        .toList())
                .pagamento(pedido.getPagamento())
                .valorTotal(pedido.getValorTotalPedido())
                .build();
    }

    public PedidoStatusResponseDTO toStatusDto(Pedido pedido) {
        return PedidoStatusResponseDTO.builder()
                .idPedido(pedido.getIdPedido())
                .codigoPedido(pedido.getCodigoPedido())
                .status(!Objects.isNull(pedido.getSituacaoPedido()) ? pedido.getSituacaoPedido().name() : null)
                .horarioInicio(pedido.getHorarioInicio())
                .horarioFinalizacao(pedido.getHorarioFinalizacao())
                .valorTotal(pedido.getValorTotalPedido())
                .build();
    }
}
