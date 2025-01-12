package com.tech.challenge.fastfood.core.application.services;

import com.tech.challenge.fastfood.core.application.ports.ClienteRepositoryPort;
import com.tech.challenge.fastfood.core.application.ports.ClienteServicePort;
import com.tech.challenge.fastfood.core.application.services.exceptions.ClienteExistenteException;
import com.tech.challenge.fastfood.core.application.services.exceptions.ClienteNotFoundException;
import com.tech.challenge.fastfood.core.application.services.exceptions.InvalidCpfException;
import com.tech.challenge.fastfood.core.domain.Cliente;
import jakarta.transaction.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class ClienteService implements ClienteServicePort {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    @Transactional
    public Cliente criarCliente(Cliente cliente) {
        if (isInValidCPF(cliente.getCpf())) {
            throw new InvalidCpfException("CPF Inserido não é válido");
        }
        Optional<Cliente> clienteExiste = clienteRepositoryPort.obterClientePorCPF(cliente.getCpf());
        if(clienteExiste.isPresent()) {
            throw new ClienteExistenteException("já existe um cliente com mesmo CPF:" + cliente.getCpf());
        }
        return clienteRepositoryPort.criarCliente(cliente);
    }

    @Override
    public Cliente obterCliente(String cpf) {
        if (cpf.isBlank() || cpf.isEmpty()) {
            throw new IllegalArgumentException("Cpf não pode ser vazio ou nulo!");
        }
        if (isInValidCPF(cpf)) {
            throw new InvalidCpfException("CPF Inserido não é válido");
        }
        return clienteRepositoryPort.obterClientePorCPF(cpf)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado: " + cpf));
    }

    @Override
    public Cliente editarCliente(Cliente cliente) {
        if (isInValidCPF(cliente.getCpf())) {
            throw new InvalidCpfException("CPF Inserido não é válido");
        }

        Cliente clienteEditado = clienteRepositoryPort.editarCliente(cliente);

        if (Objects.isNull(clienteEditado)) {
            throw new ClienteNotFoundException("Cliente não encontrado: " + cliente.getCpf());
        }
        return clienteEditado;
    }

    @Override
    public void deletarCliente(Long id) {
        Optional<Cliente> cliente = clienteRepositoryPort.obterPorId(id);
        if (cliente.isEmpty()) {
            throw new ClienteNotFoundException("Cliente não encontrado");
        }
        clienteRepositoryPort.deletarCliente(cliente.get().getId());
    }

    private boolean isInValidCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return true;
        }
        int firstDigit = calculateVerificationDigit(cpf, 10);
        int secondDigit = calculateVerificationDigit(cpf, 11);
        return !cpf.endsWith(String.valueOf(firstDigit) + secondDigit);
    }

    private int calculateVerificationDigit(String cpf, int weight) {
        int sum = IntStream.range(0, weight - 1)
                .map(i -> Character.getNumericValue(cpf.charAt(i)) * (weight - i))
                .sum();
        int result = 11 - (sum % 11);
        return result > 9 ? 0 : result;
    }
}
