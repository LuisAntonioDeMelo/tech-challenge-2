package com.tech.challenge.fastfood.core.application.ports;

import com.tech.challenge.fastfood.core.domain.Cliente;

public interface ClienteServicePort {
    public Cliente criarCliente(Cliente cliente);
    public Cliente obterCliente(String cpf);
    public Cliente editarCliente(Cliente cliente);
    public void deletarCliente(Long id);
}
