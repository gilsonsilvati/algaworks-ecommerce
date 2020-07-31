package com.algaworks.ecommerce.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedidoId implements Serializable {

    // PK
    @EqualsAndHashCode.Include
    private Integer pedidoId;

    // PK
    @EqualsAndHashCode.Include
    private Integer produtoId;

}
