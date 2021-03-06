package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.base.EntidadeBase;
import com.algaworks.ecommerce.model.enums.StatusPagamento;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pagamento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pagamento")
@Getter @Setter
public abstract class Pagamento extends EntidadeBase {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pagamento_pedido"))
    private Pedido pedido;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

}
