package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoId id;

    @MapsId("pedidoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_pedido_pedido"))
    private Pedido pedido;

    @MapsId("produtoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_item_pedido_produto"))
    private Produto produto;

    @Column(name = "preco_produto", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoProduto;

    @Column(nullable = false)
    private Integer quantidade;

}
