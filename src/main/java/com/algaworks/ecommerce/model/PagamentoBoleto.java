package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("boleto")
@Getter @Setter
public class PagamentoBoleto extends Pagamento {

    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;

}
