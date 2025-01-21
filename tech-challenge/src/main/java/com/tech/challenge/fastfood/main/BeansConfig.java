package com.tech.challenge.fastfood.main;

import com.tech.challenge.fastfood.application.gateway.*;
import com.tech.challenge.fastfood.application.usecases.cliente.ClienteUseCase;
import com.tech.challenge.fastfood.application.usecases.cliente.interactors.ClienteInteractor;
import com.tech.challenge.fastfood.application.usecases.pagamento.ConsultarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.pagamento.CriarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.pagamento.ProcessarPagamentoUseCase;
import com.tech.challenge.fastfood.application.usecases.pagamento.interactors.ConsultarPagamentoInteractor;
import com.tech.challenge.fastfood.application.usecases.pagamento.interactors.CriarPagamentoInteractor;
import com.tech.challenge.fastfood.application.usecases.pagamento.interactors.ProcessarPagamentoInteractor;
import com.tech.challenge.fastfood.application.usecases.pagamento.pagamentoStrategy.PagamentoStrategy;
import com.tech.challenge.fastfood.application.usecases.pedidos.FilaPedidosUseCase;
import com.tech.challenge.fastfood.application.usecases.pedidos.ListarPedidosPorSituacaoUseCase;
import com.tech.challenge.fastfood.application.usecases.pedidos.PedidoUseCase;
import com.tech.challenge.fastfood.application.usecases.pedidos.interactors.FilaPedidoInteractor;
import com.tech.challenge.fastfood.application.usecases.pedidos.interactors.ListarPedidosPorSituacaoInteractor;
import com.tech.challenge.fastfood.application.usecases.pedidos.interactors.PedidoInteractor;
import com.tech.challenge.fastfood.application.usecases.produto.ProdutoUseCase;
import com.tech.challenge.fastfood.application.usecases.produto.interactors.ProdutoInteractor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class BeansConfig {


    @Bean
    public ClienteUseCase clienteUseCase(ClienteGateway clienteGateway) {
        return new ClienteInteractor(clienteGateway);
    }

    @Bean
    public ProdutoUseCase produtoUseCase(ProdutoGateway produtoGateway) {
        return new ProdutoInteractor(produtoGateway);
    }

    @Bean
    public PedidoUseCase pedidoUseCase(PedidoGateway pedidoGateway) {
        return new PedidoInteractor(pedidoGateway);
    }

    @Bean
    public FilaPedidosUseCase filaPedidosUseCase(PedidoGateway pedidoGateway) {
        return new FilaPedidoInteractor(pedidoGateway);
    }

    @Bean
    public ProcessarPagamentoUseCase processarPagamentoUseCase(Map<String, PagamentoStrategy> strategies, PedidoGateway pedidoGateway) {
        return new ProcessarPagamentoInteractor(strategies, pedidoGateway);
    }

    @Bean
    public ConsultarPagamentoUseCase consultarPagamentoUseCase(PagamentoGateway pagamentoGateway) {
        return new ConsultarPagamentoInteractor(pagamentoGateway);
    }

    @Bean
    public CriarPagamentoUseCase criarPagamentoUseCase(PedidoGateway pedidoGateway) {
        return new CriarPagamentoInteractor(pedidoGateway);
    }

    @Bean
    public ListarPedidosPorSituacaoUseCase listarPedidosPorSituacaoUseCase(PedidoGateway pedidoGateway) {
        return new ListarPedidosPorSituacaoInteractor(pedidoGateway);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
