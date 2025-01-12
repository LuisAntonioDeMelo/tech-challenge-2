package com.tech.challenge.fastfood.adapter.driven.converters;

import com.tech.challenge.fastfood.adapter.driver.api.dtos.PedidoRequest;
import com.tech.challenge.fastfood.adapter.driver.api.dtos.PedidoResponseDTO;
import com.tech.challenge.fastfood.adapter.driver.api.dtos.ProdutoDTO;
import com.tech.challenge.fastfood.core.domain.Cliente;
import com.tech.challenge.fastfood.core.domain.Pedido;
import com.tech.challenge.fastfood.core.domain.Produto;
import com.tech.challenge.fastfood.core.domain.SituacaoPedido;
import org.springframework.stereotype.Component;

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
                .map(p -> new Produto(p.getId(), p.getEAN()))
                .toList());
        return pedido;
    }

    public PedidoResponseDTO toDto(Pedido pedido) {
        return PedidoResponseDTO.builder()
                .idPedido(pedido.getIdPedido())
                .codigoPedido(pedido.getCodigoPedido())
                .situacaoPedido(pedido.getSituacaoPedido().name())
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
                .valorTotal(pedido.getValorTotalPedido())
                .build();
    }
}
