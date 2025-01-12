package com.tech.challenge.fastfood.core.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;

}
