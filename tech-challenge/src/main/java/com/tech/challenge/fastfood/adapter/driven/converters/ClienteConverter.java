package com.tech.challenge.fastfood.adapter.driven.converters;

import com.tech.challenge.fastfood.adapter.driver.api.dtos.ClienteRequest;
import com.tech.challenge.fastfood.adapter.driver.api.dtos.ClienteResponseDTO;
import com.tech.challenge.fastfood.core.domain.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter {


    public Cliente toDomain(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setCpf(clienteRequest.cpf());
        cliente.setNome(clienteRequest.nome());
        cliente.setEmail(clienteRequest.email());
        cliente.setTelefone(clienteRequest.telefone());
        return cliente;
    }

    public Cliente toDomain(ClienteRequest clienteRequest, Long id) {
        Cliente cliente = toDomain(clienteRequest);
        cliente.setId(id);
        return cliente;
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
