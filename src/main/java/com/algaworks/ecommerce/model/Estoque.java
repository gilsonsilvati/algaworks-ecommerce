package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "estoque")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estoque {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

}
