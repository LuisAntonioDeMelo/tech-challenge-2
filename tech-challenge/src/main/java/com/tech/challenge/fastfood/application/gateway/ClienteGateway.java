package com.tech.challenge.fastfood.application.gateway;

import com.tech.challenge.fastfood.domain.Cliente;

import java.util.Optional;

public interface ClienteGateway {

    public Cliente criarCliente(Cliente cliente);
    public Optional<Cliente> obterClientePorCPF(String cliente);
    public Cliente editarCliente(Cliente cliente);
    public void deletarCliente(Long id);
    public Optional<Cliente> obterPorId(Long id);
}
