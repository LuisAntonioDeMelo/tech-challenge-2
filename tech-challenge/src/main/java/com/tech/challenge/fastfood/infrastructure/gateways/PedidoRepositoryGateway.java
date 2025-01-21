package com.tech.challenge.fastfood.infrastructure.gateways;


import com.tech.challenge.fastfood.application.gateway.PedidoGateway;
import com.tech.challenge.fastfood.application.usecases.pedidos.exceptions.PedidoNaoEncontratoException;
import com.tech.challenge.fastfood.domain.*;
import com.tech.challenge.fastfood.infrastructure.persistence.cliente.ClienteEntity;
import com.tech.challenge.fastfood.infrastructure.persistence.pagamento.PagamentoEntity;
import com.tech.challenge.fastfood.infrastructure.persistence.pedido.PedidoEntity;
import com.tech.challenge.fastfood.infrastructure.persistence.pedido.PedidoRepository;
import com.tech.challenge.fastfood.infrastructure.persistence.pedido.SituacaoPedidoEnum;
import com.tech.challenge.fastfood.infrastructure.persistence.produto.ProdutoEntity;
import com.tech.challenge.fastfood.infrastructure.persistence.produto.ProdutoRepository;
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
    public List<Pedido> listarPedidosPorSituacao(String situacao) {
//        if(Objects.isNull(situacao)){
//            List<PedidoEntity> pedidos = pedidoRepository.findAll().stream()
//                    .filter(Objects::nonNull)
//                    .filter(p -> p.getSituacaoPedidoEnum().compareTo(SituacaoPedidoEnum.FINALIZADO) < 0)
//                    .sorted(Comparator
//                            .comparing((PedidoEntity p) -> {
//                                if (p.getSituacaoPedidoEnum() == SituacaoPedidoEnum.PRONTO) return 1;
//                                else if (p.getSituacaoPedidoEnum() == SituacaoPedidoEnum.EM_PREPARACAO) return 2;
//                                else return 3;
//                            })
//                            .thenComparing(PedidoEntity::getHorarioInicio))
//                    .toList();
//            return pedidos.stream().map(p -> modelMapper.map(p, Pedido.class)).toList();
//        }
        Integer situacaoPedido = situacao != null ? SituacaoPedidoEnum.obter(situacao).getCodigo() : null;
        List<String> situacoesNotIn = List.of("CRIADO", "INCIAR_PREPARACAO", "MONTAGEM", "FINALIZADO");
        List<PedidoEntity> pedidos = pedidoRepository.listarPedidosPorSituacao(situacaoPedido, situacoesNotIn);
        return pedidos.stream().map(p -> modelMapper.map(p, Pedido.class)).toList();
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        ClienteEntity clienteEntity = modelMapper.map(pedido.getCliente(), ClienteEntity.class);

        entity.setClienteEntity(clienteEntity);
        entity.setCodigoPedido(UUID.randomUUID().toString());

        List<ProdutoEntity> produtoStok = produtoRepository.findAllById(pedido.getProdutos().stream().map(Produto::getId).toList());
        entity.setProdutos(produtoStok);

        entity.setHorarioInicio(LocalDateTime.now());
        entity.setSituacaoPedidoEnum(SituacaoPedidoEnum.CRIADO);

        BigDecimal valorTotalPedido = pedido.getProdutos().stream()
                .map(produto -> produtoStok.stream()
                        .filter(produtoEntity -> produtoEntity.getId().equals(produto.getId()))
                        .findFirst()
                        .map(produtoEntity -> produtoEntity.getValor().multiply(BigDecimal.valueOf(produto.getQuantidade())))
                        .orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        entity.setValorTotalPedido(valorTotalPedido);
        PedidoEntity pedidoEntity = pedidoRepository.save(entity);
        return modelMapper.map(pedidoEntity, Pedido.class);
    }

    @Override
    public Pedido alterarSituacaoPedido(Long idPedido, String situacaoPedido) {
        Optional<PedidoEntity> pedidoOpt = pedidoRepository.findById(idPedido);
        if (pedidoOpt.isPresent()) {
            PedidoEntity entity = pedidoOpt.get();

            SituacaoPedidoEnum situacaoPedidoEnum = SituacaoPedidoEnum.obter(situacaoPedido);
            entity.setSituacaoPedidoEnum(situacaoPedidoEnum);
            entity = pedidoRepository.save(entity);
            return modelMapper.map(entity, Pedido.class);
        }
        throw new PedidoNaoEncontratoException("Pedido não encontrado: " + idPedido);
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
        // TODO:  checkout não finaliza o pedido
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
        pagamentoEntity.setPedidoEntity(entity);
        pagamentoEntity.setValor(entity.getValorTotalPedido());
        pagamentoEntity.setDescricao(getDescricaoPagamento(entity));
        pagamentoEntity.setTipoPagamento(TipoPagamento.valueOf(tipoPagamento));
        entity.setPagamentoEntity(pagamentoEntity);
        pagamentoEntity.setStatusPagamento(StatusPagamento.PENDENTE);

        entity = pedidoRepository.save(entity);
        return modelMapper.map(entity, Pedido.class);
    }

    private static String getDescricaoPagamento(PedidoEntity entity) {
        return "Pedido : " + entity.getCodigoPedido() + " - " + entity.getClienteEntity().getNome() + "\n"+
                "Produtos: " + entity.getProdutos().stream().map(ProdutoEntity::getDescricao).reduce("", (a, b) -> a + ", " + b +" \n"+
                "Valor Total: " + entity.getValorTotalPedido());
    }

}
