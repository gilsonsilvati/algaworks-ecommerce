package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
@IdClass(ItemPedidoId.class)
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    // PK
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "pedido_id")
    private Integer pedidoId;

    // PK
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "produto_id")
    private Integer produtoId;

    // FK
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Pedido pedido;

    // FK
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produto;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    private Integer quantidade;

}
