package com.algaworks.ecommerce.model.enums;

import lombok.Getter;

@Getter
public enum StatusPedido {

    AGUARDANDO("Aguardando"),
    CANCELADO("Cancelado"),
    PAGO("Pago");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

}
