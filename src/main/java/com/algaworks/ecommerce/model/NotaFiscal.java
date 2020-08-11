package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.base.EntidadeBase;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nota_fiscal")
@Getter @Setter
public class NotaFiscal extends EntidadeBase {

    @MapsId // Quando nossa FK também é uma PK
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Lob
    private byte[] xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;

}
