package com.algaworks.ecommerce.model.enums;

public enum StatusPedido {

    AGUARDANDO("Aguardando"),
    CANCELADO("Cancelado"),
    PAGO("Pago");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
