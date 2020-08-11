package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.base.EntidadeBase;
import com.algaworks.ecommerce.model.enums.StatusPagamento;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pagamento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Não é necessário: valor já é default
@DiscriminatorColumn(name = "tipo_pagamento",
        discriminatorType = DiscriminatorType.STRING) // Não é necessário: valor já é default
@Getter @Setter
public abstract class Pagamento extends EntidadeBase {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

}
