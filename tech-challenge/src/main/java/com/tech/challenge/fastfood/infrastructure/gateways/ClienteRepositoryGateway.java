package com.tech.challenge.fastfood.infrastructure.gateways;

import com.tech.challenge.fastfood.application.usecases.cliente.exceptions.ClientePersistenceException;
import com.tech.challenge.fastfood.application.gateway.ClienteGateway;
import com.tech.challenge.fastfood.domain.Cliente;
import com.tech.challenge.fastfood.infrastructure.persistence.cliente.ClienteEntity;
import com.tech.challenge.fastfood.infrastructure.persistence.cliente.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteRepositoryGateway implements ClienteGateway {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    @ExceptionHandler(ClientePersistenceException.class)
    public Cliente criarCliente(Cliente cliente) {
        ClienteEntity clienteEntity= clienteRepository.save(modelMapper.map(cliente, ClienteEntity.class));
        return modelMapper.map(clienteEntity, Cliente.class);
    }

    @Override
    public Cliente editarCliente(Cliente cliente) {
        Optional<ClienteEntity> clienteEntityDb = clienteRepository.findById(cliente.getId());
        if(clienteEntityDb.isPresent()) {
            ClienteEntity clienteEntity =  clienteEntityDb.get();
            clienteEntity.setNome(cliente.getNome());
            clienteEntity.setCpf(cliente.getCpf());
            clienteEntity.setTelefone(cliente.getTelefone());
            return modelMapper.map(clienteRepository.save(clienteEntity), Cliente.class);
        }
        return modelMapper.map(clienteEntityDb, Cliente.class);
    }

    @Override
    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Optional<Cliente> obterPorId(Long id) {
        return Optional.of(modelMapper.map(clienteRepository.findById(id), Cliente.class));
    }

    @Override
    public Optional<Cliente> obterClientePorCPF(String cpf) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findByCpf(cpf);
        return clienteEntity.map(entity -> modelMapper.map(entity, Cliente.class));
    }

}
