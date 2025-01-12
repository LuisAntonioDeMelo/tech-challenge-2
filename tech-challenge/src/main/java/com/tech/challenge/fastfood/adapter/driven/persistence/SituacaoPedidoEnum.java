package com.tech.challenge.fastfood.adapter.driven.persistence;
import lombok.Getter;

@Getter
public enum SituacaoPedidoEnum {
    INCIAR_PREPARACAO(1, "Iniciar Preparação"),
    EM_PREPARACAO(2, "Em Preparação"),
    MONTAGEM(3, "Montagem"),
    EM_FILA_RETIRADA(4,"Em fila Retirada"),
    FINALIZADO(5, "Finalizado");

    private final Integer codigo;
    private final String descricao;

    SituacaoPedidoEnum(Integer codigo, String descricao) {
        this.codigo =  codigo;
        this.descricao = descricao;
    }

}
