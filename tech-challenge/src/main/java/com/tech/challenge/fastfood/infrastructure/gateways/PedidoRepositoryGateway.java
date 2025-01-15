package com.tech.challenge.fastfood.infrastructure.gateways;


import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.exceptions.PedidoNaoEncontratoException;
import com.tech.challenge.fastfood.domain.Pedido;
import com.tech.challenge.fastfood.domain.Produto;
import com.tech.challenge.fastfood.domain.TipoPagamento;
import com.tech.challenge.fastfood.infrastructure.persistence.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryGateway implements PedidoGateway {

    private final ModelMapper modelMapper;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll().stream().map(p -> modelMapper.map(p, Pedido.class)).toList();
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        ClienteEntity clienteEntity = modelMapper.map(pedido.getCliente(), ClienteEntity.class);

        entity.setClienteEntity(clienteEntity);
        entity.setCodigoPedido(UUID.randomUUID().toString());

        List<ProdutoEntity> produtoEntities = produtoRepository.findAllById(pedido.getProdutos().stream().map(Produto::getId).toList());
        entity.setProdutos(produtoEntities);

        entity.setHorarioInicio(LocalDateTime.now());
        entity.setSituacaoPedidoEnum(SituacaoPedidoEnum.CRIADO);
        entity.setValorTotalPedido(produtoEntities.stream().filter(Objects::nonNull).map(ProdutoEntity::getValor).reduce(BigDecimal.ZERO, BigDecimal::add));


        entity.setPagamentoEntity(null);

        PedidoEntity pedidoEntity = pedidoRepository.save(entity);
        return modelMapper.map(pedidoEntity, Pedido.class);
    }

    @Override
    public Pedido alterarSituacaoPedido(Long idPedido, String situacaoPedido) {
        Optional<PedidoEntity> pedidoOpt = pedidoRepository.findById(idPedido);
        if (pedidoOpt.isPresent()) {
            PedidoEntity entity = pedidoOpt.get();
            SituacaoPedidoEnum situacaoPedidoEnum = Arrays.stream(SituacaoPedidoEnum.values())
                    .filter(cp -> cp.name().equalsIgnoreCase(situacaoPedido))
                    .findAny().get();
            entity.setSituacaoPedidoEnum(situacaoPedidoEnum);
            entity = pedidoRepository.save(entity);
            return modelMapper.map(entity, Pedido.class);
        }
        return modelMapper.map(pedidoOpt.get(), Pedido.class);
    }

    @Override
    public Pedido obterPedidoPorCPF(String cpf) {
        PedidoEntity entity = pedidoRepository.oterPedidoPorCPF(cpf);
        return entity != null ? modelMapper.map(entity, Pedido.class) : null;
    }

    @Override
    public Pedido checkoutPedido(Long idPedido) {
        Optional<PedidoEntity> pedidoOpt = pedidoRepository.findById(idPedido);
        if (pedidoOpt.isEmpty()) {
            throw new PedidoNaoEncontratoException("Pedido não encontrado");
        }
        PedidoEntity entity = pedidoOpt.get();
//        entity.setSituacaoPedidoEnum(SituacaoPedidoEnum.FINALIZADO);
//        entity.setHorarioFinalizacao(LocalDateTime.now());
//        entity = pedidoRepository.save(entity);
        return modelMapper.map(entity, Pedido.class);
    }

    @Override
    public Pedido alterarPedido(Pedido pedido) {
        PedidoEntity entity = modelMapper.map(pedido, PedidoEntity.class);
        entity = pedidoRepository.save(entity);
        return modelMapper.map(entity, Pedido.class);
    }

    @Override
    public Pedido obterPedidoPorId(Long idPedido) {
        Optional<PedidoEntity> pedidoOpt = pedidoRepository.findById(idPedido);
        return pedidoOpt.map(p -> modelMapper.map(p, Pedido.class)).orElse(null);
    }

    @Override
    public Pedido criarPagamentoPedido(Long id, String tipoPagamento) {
        Optional<PedidoEntity> pedidoOpt = pedidoRepository.findById(id);

        if (pedidoOpt.isEmpty()) {
            throw new PedidoNaoEncontratoException("Pedido não encontrado");
        }

        PedidoEntity entity = pedidoOpt.get();
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        pagamentoEntity.setValor(entity.getValorTotalPedido());
        pagamentoEntity.setDescricao("");
        pagamentoEntity.setTipoPagamento(TipoPagamento.valueOf(tipoPagamento));
        entity.setPagamentoEntity(pagamentoEntity);
        entity = pedidoRepository.save(entity);
        return modelMapper.map(entity, Pedido.class);
    }

}
