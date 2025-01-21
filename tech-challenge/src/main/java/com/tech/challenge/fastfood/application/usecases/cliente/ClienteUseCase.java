package com.tech.challenge.fastfood.application.usecases.cliente;


import com.tech.challenge.fastfood.domain.Cliente;

public interface ClienteUseCase {
    public Cliente criarCliente(Cliente cliente);
    public Cliente obterCliente(String cpf);
    public Cliente editarCliente(Cliente cliente);
    public void deletarCliente(Long id);
}
