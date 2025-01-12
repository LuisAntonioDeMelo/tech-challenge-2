package com.tech.challenge.fastfood.core.application.ports;

import com.tech.challenge.fastfood.core.domain.Cliente;

import java.util.Optional;

public interface ClienteRepositoryPort {

    public Cliente criarCliente(Cliente cliente);
    public Optional<Cliente> obterClientePorCPF(String cliente);
    public Cliente editarCliente(Cliente cliente);
    public void deletarCliente(Long id);
    public Optional<Cliente> obterPorId(Long id);
}
