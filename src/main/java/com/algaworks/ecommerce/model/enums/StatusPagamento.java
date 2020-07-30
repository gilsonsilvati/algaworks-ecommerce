package com.algaworks.ecommerce.model.enums;

import lombok.Getter;

@Getter
public enum StatusPagamento {

    CANCELADO("Cancelado"),
    PROCESSANDO("Processando"),
    RECEBIDO("Recebido");

    private final String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

}
