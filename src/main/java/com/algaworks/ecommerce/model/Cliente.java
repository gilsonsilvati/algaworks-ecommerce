package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.enums.Sexo;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cliente")
public class Cliente {

    @EqualsAndHashCode.Include
    @NonNull
    @Id
    private Integer id;

    @NonNull
    private String nome;

    private Sexo sexo;

}
