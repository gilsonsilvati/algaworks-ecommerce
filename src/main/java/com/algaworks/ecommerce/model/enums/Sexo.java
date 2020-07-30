package com.algaworks.ecommerce.model.enums;

import lombok.Getter;

@Getter
public enum Sexo {

    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private final String descricao;

    Sexo(String descricao) {
        this.descricao = descricao;
    }

}
