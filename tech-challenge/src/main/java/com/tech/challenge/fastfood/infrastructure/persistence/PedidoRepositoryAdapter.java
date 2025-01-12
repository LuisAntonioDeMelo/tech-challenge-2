package com.tech.challenge.fastfood.infrastructure.persistence;

import com.tech.challenge.fastfood.adapter.driven.persistence.ClienteEntity;
import com.tech.challenge.fastfood.adapter.driven.persistence.PedidoEntity;
import com.tech.challenge.fastfood.adapter.driven.persistence.ProdutoEntity;
import com.tech.challenge.fastfood.adapter.driven.persistence.SituacaoPedidoEnum;
import com.tech.challenge.fastfood.application.exception.application.ports.PedidoRepositoryPort;
import com.tech.challenge.fastfood.application.exception.application.services.exceptions.PedidoNaoEncontratoException;
import com.tech.challenge.fastfood.domain.Pedido;
import com.tech.challenge.fastfood.domain.Produto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

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
        entity.setSituacaoPedidoEnum(SituacaoPedidoEnum.INCIAR_PREPARACAO);
        entity.setValorTotalPedido(produtoEntities.stream().filter(Objects::nonNull).map(ProdutoEntity::getValor).reduce(BigDecimal.ZERO, BigDecimal::add));

        PedidoEntity pedidoEntity = pedidoRepository.save(entity);
        return modelMapper.map(pedidoEntity, Pedido.class);
    }

    @Override
    public Pedido alterarSituacaoPedido(Long idPedido, String situacaoPedido) {
        Optional<PedidoEntity> pedidoOpt = pedidoRepository.findById(idPedido);
        if (pedidoOpt.isPresent()) {
            PedidoEntity entity = pedidoOpt.get();
            SituacaoPedidoEnum situacaoPedidoEnum = Arrays.stream(SituacaoPedidoEnum.values()).filter(cp -> cp.name().equalsIgnoreCase(situacaoPedido)).findAny().get();
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
        entity.setSituacaoPedidoEnum(SituacaoPedidoEnum.FINALIZADO);
        entity.setHorarioFinalizacao(LocalDateTime.now());
        entity = pedidoRepository.save(entity);
        return modelMapper.map(entity, Pedido.class);
    }

}
