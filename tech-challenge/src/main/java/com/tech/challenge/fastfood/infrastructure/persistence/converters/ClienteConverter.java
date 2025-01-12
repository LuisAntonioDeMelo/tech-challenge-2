package com.tech.challenge.fastfood.infrastructure.persistence.converters;

import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ClienteRequest;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ClienteResponseDTO;
import com.tech.challenge.fastfood.domain.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter {


    public Cliente toDomain(ClienteRequest clienteRequest) {
       return new Cliente( null ,clienteRequest.cpf(),clienteRequest.nome(), clienteRequest.email(), clienteRequest.telefone());
    }

    public Cliente toDomain(Long id, ClienteRequest clienteRequest) {
        return new Cliente(id, clienteRequest.cpf(),clienteRequest.nome(), clienteRequest.email(), clienteRequest.telefone());
    }

    public ClienteResponseDTO toDto(Cliente cliente) {
        return ClienteResponseDTO
                .builder()
                .codigoCliente(cliente.id())
                .cpf(cliente.cpf())
                .nomeCliente(cliente.nome())
                .emailCliente(cliente.email())
                .numeroTelefone(cliente.telefone())
                .build();
    }
}
