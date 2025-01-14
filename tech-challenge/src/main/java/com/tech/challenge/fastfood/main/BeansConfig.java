package com.tech.challenge.fastfood.main;

import com.tech.challenge.fastfood.application.gateway.*;
import com.tech.challenge.fastfood.application.usecases.*;
import com.tech.challenge.fastfood.application.usecases.interactors.*;
import com.tech.challenge.fastfood.application.usecases.patterns.PagamentoStrategy;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.http.HttpClient;
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
    public ProcessarPagamentoUseCase processarPagamentoUseCase(Map<String, PagamentoStrategy> strategies) {
        return new ProcessarPagamentoInteractor(strategies);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
