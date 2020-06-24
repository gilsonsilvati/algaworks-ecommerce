package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.enums.Sexo;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

}
