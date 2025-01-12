package com.tech.challenge.fastfood.infra.config;

import com.tech.challenge.fastfood.core.application.ports.*;
import com.tech.challenge.fastfood.core.application.services.ClienteService;
import com.tech.challenge.fastfood.core.application.services.FilaPedidosService;
import com.tech.challenge.fastfood.core.application.services.PedidoService;
import com.tech.challenge.fastfood.core.application.services.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public ClienteServicePort clienteServicePort(ClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteService(clienteRepositoryPort);
    }

    @Bean
    public ProdutoServicePort produtoServicePort(ProdutoRepositoryPort produtoRepositoryPort) {
        return new ProdutoService(produtoRepositoryPort);
    }

    @Bean
    public PedidoServicePort pedidoServicePort(PedidoRepositoryPort pedidoRepositoryPort) {
        return new PedidoService(pedidoRepositoryPort);
    }

    @Bean
    public FilaPedidosServicePort filaPedidosServicePort(PedidoRepositoryPort pedidoRepositoryPort, ClienteRepositoryPort clienteRepositoryPort) {
        return new FilaPedidosService(pedidoRepositoryPort, clienteRepositoryPort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
