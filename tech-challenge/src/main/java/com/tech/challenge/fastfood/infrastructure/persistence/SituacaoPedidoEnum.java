package com.tech.challenge.fastfood.infrastructure.persistence;
import lombok.Getter;

@Getter
public enum SituacaoPedidoEnum {
    CRIADO(1, "Criado"),
    INCIAR_PREPARACAO(2, "Iniciar Preparação"),
    EM_PREPARACAO(3, "Em Preparação"),
    MONTAGEM(4, "Montagem"),
    EM_FILA_RETIRADA(5,"Em fila Retirada"),
    FINALIZADO(6, "Finalizado");

    private final Integer codigo;
    private final String descricao;

    SituacaoPedidoEnum(Integer codigo, String descricao) {
        this.codigo =  codigo;
        this.descricao = descricao;
    }

}
