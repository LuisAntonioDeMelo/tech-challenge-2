package com.tech.challenge.fastfood.application.exception.application.ports;

import com.tech.challenge.fastfood.domain.Cliente;

public interface ClienteServicePort {
    public Cliente criarCliente(Cliente cliente);
    public Cliente obterCliente(String cpf);
    public Cliente editarCliente(Cliente cliente);
    public void deletarCliente(Long id);
}
