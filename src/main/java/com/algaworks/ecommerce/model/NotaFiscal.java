package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.base.EntidadeBase;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nota_fiscal")
@Getter @Setter
public class NotaFiscal extends EntidadeBase {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_nota_fiscal_pedido"))
    private Pedido pedido;

    @Column(nullable = false)
    @Lob
    private byte[] xml;

    @Column(name = "data_emissao", nullable = false)
    private Date dataEmissao;

}
