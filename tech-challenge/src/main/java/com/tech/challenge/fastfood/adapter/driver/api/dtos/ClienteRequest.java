package com.tech.challenge.fastfood.adapter.driver.api.dtos;

public record ClienteRequest(
        String cpf,
        String nome,
        String email,
        String telefone) {
}
