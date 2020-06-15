package com.algaworks.ecommerce.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(name = "categoria_pai_id")
    private Integer categoriaPaiId;

}
