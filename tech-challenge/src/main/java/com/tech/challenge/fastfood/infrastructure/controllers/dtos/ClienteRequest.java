package com.tech.challenge.fastfood.infrastructure.controllers.dtos;

public record ClienteRequest(
        String cpf,
        String nome,
        String email,
        String telefone) {
}
