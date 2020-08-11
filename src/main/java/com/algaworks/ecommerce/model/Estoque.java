package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.base.EntidadeBase;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "estoque")
@Getter @Setter
public class Estoque extends EntidadeBase {

    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

}
