package com.tech.challenge.fastfood.main;

import com.tech.challenge.fastfood.application.gateway.*;
import com.tech.challenge.fastfood.application.usecases.*;
import com.tech.challenge.fastfood.application.usecases.interactors.ClienteInteractor;
import com.tech.challenge.fastfood.application.usecases.interactors.FilaPedidoInteractor;
import com.tech.challenge.fastfood.application.usecases.interactors.PedidoInteractor;
import com.tech.challenge.fastfood.application.usecases.interactors.ProdutoInteractor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
