package com.algaworks.ecommerce.model.enums;

public enum StatusPagamento {

    CANCELADO("Cancelado"),
    PROCESSANDO("Processando"),
    RECEBIDO("Recebido");

    private final String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
