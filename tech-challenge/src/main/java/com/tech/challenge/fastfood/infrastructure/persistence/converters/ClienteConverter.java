package com.tech.challenge.fastfood.infrastructure.persistence.converters;


import com.tech.challenge.fastfood.domain.Cliente;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ClienteRequest;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ClienteResponseDTO;
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
                .codigoCliente(cliente.getId())
                .cpf(cliente.getCpf())
                .nomeCliente(cliente.getNome())
                .emailCliente(cliente.getEmail())
                .numeroTelefone(cliente.getTelefone())
                .build();
    }
}
